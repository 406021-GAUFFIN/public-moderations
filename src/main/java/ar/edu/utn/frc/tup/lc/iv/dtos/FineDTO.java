package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing a moderation process in the system.
 * Moderation includes details about
 * the process, its state, and related infractions.
 */
@NoArgsConstructor
@Getter
@Setter
public class FineDTO extends BaseDTO {

    /**
     * The ID of the plot associated with the moderation process.
     */

    private Long plotId;

    /**
     * The current state of the moderation
     * process, represented by {@link FineState}.
     */
    private FineState fineState;

    /**
     * The type of sanction linked to the moderation process.
     */
    private SanctionType sanctionType;
    /**
     * The list of infractions associated
     * with the moderation process.
     */
    private List<ReducedInfractionDTO> infractions;




    }

