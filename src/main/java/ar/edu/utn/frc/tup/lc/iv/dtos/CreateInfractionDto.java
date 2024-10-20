package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for the creation of the infraction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInfractionDto {
    /**
     * The ID of the plot who committed the infraction.
     */
    private Long plotId;

    /**
     * A description of the infraction.
     */
    private String description;

    /**
     * The type of sanction associated with the infraction.
     */
    private Long sanctionTypeId;

    /**
     * The list of claims IDs related to the infraction.
     */
    private List<Long> claimsId;
}
