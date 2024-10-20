package ar.edu.utn.frc.tup.lc.iv.services.impl;


import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.clients.ExpensesClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineUpdateStateDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.PlotDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ExpensesClientException;
import ar.edu.utn.frc.tup.lc.iv.dtos.CreateFineDTO;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType.SanctionTypeJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.FineService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineSpecification.inModerationState;
import static ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineSpecification.inSanctionType;

/**
 * Service to manage infractions.
 */
@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {

    /**
     * Fine repository.
     */
    private final FineJpaRepository fineJpaRepository;

    /**
     * Sanction Type repository.
     */
    private final SanctionTypeJpaRepository sanctionTypeJpaRepository;

    /**
     * Model mapper.
     */
    private final ModelMapper modelMapper;



    /**
     * Expenses client object.
     */
    private final ExpensesClient expensesClient;

    /**
     * Cadastre client object.
     */
    private final CadastreClient cadastreClient;



    /**
     * TGet all fines, paginated and filtered.
     * @param pageable Pageable object with page size and current page
     * @param fineState list of fine state to filter
     * @param sanctionTypes list of sanction types to filter
     *
     * @return all fines paginated and filtered
     */
    @Override
    public Page<FineDTO> getAllFines(Pageable pageable, List<FineState> fineState, List<Long> sanctionTypes) {

        Specification<FineEntity> filters = Specification.where(CollectionUtils.isEmpty(fineState) ? null : inModerationState(fineState))
        .and(CollectionUtils.isEmpty(sanctionTypes) ? null : inSanctionType(sanctionTypes));
        Page<FineEntity> fineEntityPage = fineJpaRepository.findAll(filters, pageable);

        return  fineEntityPage.map(fineEntity -> (modelMapper.map(fineEntity, FineDTO.class)));
    }

    /**
     * Get one fine by id.
     * @param id of the fine to get
     *
     * @return fine dto
     */

    @Override
    public FineDTO getById(Long id) {
        Optional<FineEntity> fineEntity = fineJpaRepository.findById(id);

        if (fineEntity.isEmpty()) {
            throw new EntityNotFoundException("Fine Not Found");
        }
        return modelMapper.map(fineEntity.get(), FineDTO.class);
    }

    /**
     * Create fine.
     * @param  createFineDTO fine to create
     * @return created fine dto
     */
    @Override
    public FineDTO postFine(CreateFineDTO createFineDTO) {
        FineEntity fineEntity = new FineEntity();
        SanctionTypeEntity sanctionTypeEntity = sanctionTypeJpaRepository.findById(createFineDTO.getSanctionTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Sanction Type not found"));

        // Validar que el plot exista contra cadastre service

        PlotDTO plotDTO = cadastreClient.getPlotById(createFineDTO.getPlotId());

        if (plotDTO == null) {
            throw  new EntityNotFoundException("Plot not found");
        }



        fineEntity.setPlotId(createFineDTO.getPlotId());
        fineEntity.setFineState(FineState.ON_ASSEMBLY);
        fineEntity.setSanctionType(sanctionTypeEntity);
        fineEntity.setInfractions(createFineDTO.getInfractions());
        for (InfractionEntity infraction : createFineDTO.getInfractions()) {
            infraction.setFine(fineEntity);
        }
        fineEntity = fineJpaRepository.save(fineEntity);
        return modelMapper.map(fineJpaRepository.save(fineEntity), FineDTO.class);

    }

    /**
     * Updates the state of a fine based on the provided request.
     *
     * @param request the DTO containing the fine ID and the new state
     * @return the updated FineDTO
     * @throws EntityNotFoundException if the fine is not found or
     * the state transition is not allowed
     */
    @Override
    @Transactional
    public FineDTO updateFineState(FineUpdateStateDTO request) {
        Optional<FineEntity> fineEntity = fineJpaRepository.findById(request.getId());

        if (fineEntity.isEmpty()) {
            throw new EntityNotFoundException("Fine Not Found");
        }

        FineEntity fineToUpdate = fineEntity.get();
        FineState newState = request.getFineState();


        fineToUpdate.getFineState().validateTransition(newState);
        fineToUpdate.setFineState(newState);


        FineEntity updatedFine = fineJpaRepository.save(fineToUpdate);

        if (newState == FineState.APPROVED) {
            sendFineToExpense(updatedFine);
        }

        return modelMapper.map(updatedFine, FineDTO.class);

        }
    /**
     * Sends fine data from the given {@link FineEntity} to the expenses service.
     *
     * @param fineEntity The fine data to send.
     * @throws ExpensesClientException If sending fails.
     */

     private void sendFineToExpense(FineEntity fineEntity) {
         FineExpenseDTO fineExpenseDTO = new FineExpenseDTO();
         fineExpenseDTO.setAmount(fineEntity.getSanctionType().getAmount());
         fineExpenseDTO.setFineId(fineEntity.getId());
         fineExpenseDTO.setPeriod(LocalDateTime.now());
         fineExpenseDTO.setType(fineEntity.getSanctionType().getChargeType().toString());
         fineExpenseDTO.setLotId(fineEntity.getPlotId());

        try {
            expensesClient.sendToExpenses(fineExpenseDTO);
        } catch (WebClientException e) {
            throw new ExpensesClientException("Error when sending fine to expenses: " + e.getMessage(), e);
        }


     }


}




