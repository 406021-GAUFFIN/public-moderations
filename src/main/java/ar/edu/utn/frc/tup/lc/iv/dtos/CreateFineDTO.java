package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO for the creation of the fine.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFineDTO {
    /**
     * The ID of the plot associated with the moderation process.
     */
    private Long plotId;
    /**
     * The type of sanction linked to the moderation process.
     */
    private Long sanctionTypeId;
    /**
     * The list of infractions associated
     * with the moderation process.
     */
    private List<InfractionEntity> infractions;

}
