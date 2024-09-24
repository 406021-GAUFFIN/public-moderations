package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ClaimState;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.models.Infraction;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ClaimDTO {

    private Integer userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date expiringDate;


    private SanctionType sanctionType;


    private ClaimState claimState;

    private InfractionDTO infraction;
}
