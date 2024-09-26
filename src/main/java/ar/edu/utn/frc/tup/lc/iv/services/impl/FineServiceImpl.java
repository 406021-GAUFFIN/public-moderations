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

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineJpaRepository fineJpaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Page<FineDTO> getAllFines(Pageable pageable, List<FineState> fineState, List<Long> sanctionTypes, Double price) {


        Specification<FineEntity> filters = Specification.where(price==null? null:  price(price))
                .and(CollectionUtils.isEmpty(fineState) ? null : inModerationState(fineState))
        .and(CollectionUtils.isEmpty(sanctionTypes) ? null : inSanctionType(sanctionTypes));

        Page<FineEntity> fineEntityPage = fineJpaRepository.findAll(filters, pageable);





        return  fineEntityPage.map(fineEntity -> (modelMapper.map(fineEntity, FineDTO.class)));
    }

    @Override
    public FineDTO getById(Long id) {
        Optional<FineEntity> fineEntity = fineJpaRepository.findById(id);

        if(fineEntity.isEmpty()){
            throw new EntityNotFoundException("Fine Not Found");
        }

        Fine fineModel = modelMapper.map(fineEntity.get(),Fine.class);
        FineDTO fineDTO = modelMapper.map(fineEntity.get(),FineDTO.class);


        return fineDTO;
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
