package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.clients.ExpensesClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineUpdateStateDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseResponseDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineJpaRepository;
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

import java.time.LocalDate;
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
     * Model mapper.
     */
    private final ModelMapper modelMapper;

    private final ExpensesClient expensesClient;

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

    @Override
    @Transactional
    public FineDTO updateFineState(FineUpdateStateDTO request) {
        Optional<FineEntity> fineEntity = fineJpaRepository.findById(request.getId());
        if (fineEntity.isEmpty()) {
            throw new EntityNotFoundException("Fine Not Found");
        }

        if (fineEntity.get().getFineState().equals(FineState.REJECTED)) {
            throw new EntityNotFoundException("Fine state cant be updated since its been rejected");
        }
        if (fineEntity.get().getFineState().equals(FineState.IMPUTED_ON_EXPENSE)) {
            throw new EntityNotFoundException("Fine state cant be updated since its been already imputed on expense");
        }

        FineEntity fineToUpdate = fineEntity.get();
        FineState newState = request.getFineState();

        newState.validateTransition(fineToUpdate);
        fineToUpdate.setFineState(newState);

        FineEntity updatedFine = fineJpaRepository.save(fineToUpdate);
        return modelMapper.map(updatedFine, FineDTO.class);

        }

    @Override
    @Transactional
    public FineDTO imputeFine(FineExpenseDTO request) {
        FineEntity fineEntity = fineJpaRepository.findById(request.getFineId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Fine with id " + request.getFineId() + "not found")
                );
        if(!fineEntity.getFineState().equals(FineState.APPROVED)){
            throw new IllegalArgumentException("Fine with id " + request.getFineId() + " is not approved");
        }

        if(LocalDateTime.now().isBefore(fineEntity.getLastUpdatedAt()
                .plusDays(fineEntity.getSanctionType().getValidityPeriod()))){
            throw new IllegalArgumentException("Fine with id " + request.getFineId() + " can still be "
                    + "challenged by the owner of the plot");

        }
        FineExpenseDTO fineExpenseDTO = new FineExpenseDTO();
        fineExpenseDTO.setAmount(fineEntity.getSanctionType().getPrice());
        fineExpenseDTO.setFineId(request.getFineId());
        fineExpenseDTO.setPeriod(LocalDateTime.now());
        fineExpenseDTO.setType(request.getType());
        //expensesClient.sendToExpenses(fineExpenseDTO);

        fineEntity.setFineState(FineState.IMPUTED_ON_EXPENSE);
        return modelMapper.map(fineJpaRepository.save(fineEntity), FineDTO.class);

    }
}




