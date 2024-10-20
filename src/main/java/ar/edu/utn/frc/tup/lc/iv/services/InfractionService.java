package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.CreateInfractionDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.InfractionDTO;
/**
 * Service to manage infractions.
 */
public interface InfractionService {
    /**
     * Create infraction.
     * @param  dto fine to create
     * @return created fine dto
     */
    InfractionDTO postInfraction(CreateInfractionDto dto);
}
