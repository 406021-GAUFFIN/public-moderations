package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;
import ar.edu.utn.frc.tup.lc.iv.models.Claim;
import ar.edu.utn.frc.tup.lc.iv.models.Fine;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;

import java.util.List;

public class InfractionDTO {

    private Integer userId;

    private String description;


    private InfractionState infractionState;


    private SanctionType sanctionType;

    private List<ClaimDTO> claims;

    private Fine fine;
}
