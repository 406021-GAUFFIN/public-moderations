package ar.edu.utn.frc.tup.lc.iv.dtos.external;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


/**
 * Data Transfer Object representing a fine expense.
 * Contains information about the fine to be recorded,
 * including its ID,
 * associated lot ID, realization period, amount, and type.
 */

@NoArgsConstructor
@Setter
@Getter
public class FineExpenseDTO {
    /** Id of the fine to be recorded. */
    private Long fineId;

    /** Lot ID of the lot to which the fine belongs. */
    private Long lotId;

    /** Period of realization of the fine.   */
    private LocalDateTime period;
    /** Amount of the la multa. */
    private Double amount;
    /** Id of the type of fine (e.g., fixed or percentage). */
    private String type;
}
