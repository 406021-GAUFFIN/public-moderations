package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * infraction made by multiple claim.
 */
@NoArgsConstructor
@Getter
@Setter
public class Infraction extends BaseModel {


    /**
     * The ID of the user who committed the infraction.
     */
    private Long plotId;

    /**
     * A description of the infraction.
     */
    private String description;

    /**
     * The current state of the infraction
     * represented by {@link InfractionState}.
     */
    private InfractionState infractionState;

    /**
     * The type of sanction associated with the infraction.
     */

    private SanctionType sanctionType;

    /**
     * The list of claims related to the infraction.
     */
    private List<Claim> claims;

    /**
     * The moderation process linked to the infraction.
     */
    private Fine fine;
}
