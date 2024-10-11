package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.models.CreateFine;
import ar.edu.utn.frc.tup.lc.iv.models.Fine;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.FineService;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineSpecification.*;

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
    public FineDTO postFine(CreateFine request) {
        FineEntity fineEntity = new FineEntity();
        fineEntity.setPlotId(request.plotId);
        for (FineState fineState: FineState.values())
        {
            if(fineState.getId() == request.fineState )
                fineEntity.setFineState(fineState);
        }

        SanctionTypeEntity sancion = new SanctionTypeEntity();
        //sancion = SanctionTypeService.findById(request.idSanctionType);
        //Optional<FineEntity> fineEntity = fineJpaRepository.findById(id);

        fineEntity.setSanctionType(null);
        fineEntity = fineJpaRepository.save(fineEntity);

        Fine fineModel = modelMapper.map(fineEntity,Fine.class);
        FineDTO fineDTO = modelMapper.map(fineEntity,FineDTO.class);
    return  fineDTO;
    }

}
