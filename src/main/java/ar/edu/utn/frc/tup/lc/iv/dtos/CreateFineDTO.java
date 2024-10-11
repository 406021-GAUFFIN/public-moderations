package ar.edu.utn.frc.tup.lc.iv.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateFineDTO {
    /**
     * The ID of the plot associated with the moderation process.
     */
    private Long plotId;
    /**
     * The type of sanction linked to the moderation process.
     */
    private Long sanctionTypeId;

}
