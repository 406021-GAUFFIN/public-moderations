package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing an infraction in the system.
 * An infraction is associated with a user, a description,
 * a sanction type, a moderation process, and multiple claims.
 */
@NoArgsConstructor
@Getter
@Setter
public class InfractionDTO extends BaseDTO {

    /**
     * The ID of the plot who committed the infraction.
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
    private SanctionTypeDTO sanctionType;

    /**
     * The list of claims related to the infraction.
     */
    private List<ClaimDTO> claims;

    /**
     * The moderation process linked to the infraction.
     */
    private FineDTO fine;
}
