package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ModerationState;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface FineService {
    Page<FineDTO> getAllFines(Pageable pageable, List<ModerationState> moderationState, List<SanctionType> sanctionType, Double price);


    FineDTO getById(Long id);



}
