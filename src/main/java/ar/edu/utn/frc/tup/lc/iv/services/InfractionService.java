package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.CreateInfractionDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.InfractionDTO;

public interface InfractionService {
    InfractionDTO postInfraction(CreateInfractionDto dto);
}
