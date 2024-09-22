package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.models.Fine;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.FineJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.FineService;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineJpaRepository fineJpaRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public Page<Fine> getAllFines(Pageable pageable) {
        // Usamos el método paginado del repositorio
        Page<FineEntity> fineEntityPage = fineJpaRepository.findAll(pageable);

        // Convertimos cada FineEntity en Fine usando modelMapper
        return fineEntityPage.map(fineEntity -> modelMapper.map(fineEntity, Fine.class));
    }

}
