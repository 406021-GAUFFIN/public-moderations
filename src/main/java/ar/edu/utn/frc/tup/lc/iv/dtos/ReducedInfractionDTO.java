package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Reduced DTO to avoid circular references with claims.
 */
@NoArgsConstructor
@Getter
@Setter
public class ReducedInfractionDTO extends BaseDTO {

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
    private SanctionType sanctionType;
}
