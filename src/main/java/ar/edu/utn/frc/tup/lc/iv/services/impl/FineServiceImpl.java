package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.models.Fine;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.FineJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.FineService;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineJpaRepository fineJpaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Page<FineDTO> getAllFines(Pageable pageable) {

        Page<FineEntity> fineEntityPage = fineJpaRepository.findAll(pageable);


        return fineEntityPage.map(fineEntity -> modelMapper.map(fineEntity, FineDTO.class));
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

}
