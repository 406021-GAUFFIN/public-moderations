package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ClaimState;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Claim extends BaseModel{


    /**
     * The ID of the user who made the claim.
     */
    private Integer userId;

    /**
     * The date of expiration for a claim.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date expiringDate;

    /**
     * The type of sanction related to the claim.
     */

    private SanctionTypeEntity sanctionTypeEntity;

    /**
     * The current state of the claim, represented by {@link ClaimState}.
     */
    private ClaimState claimState;

    /**
     * The infraction associated with the claim.
     */
    private Infraction infraction;
}
