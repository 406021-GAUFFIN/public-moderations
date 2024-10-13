package ar.edu.utn.frc.tup.lc.iv.dtos.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data transfer object to represent a request
 * for a charge inquiry or a list of fine charges.
 * <p>
 * This class encapsulates the details required to query a fine charge,
 * including charge ID, batch ID, description, amount, period and fine ID.
 * </p>
 */


@Setter
@Getter
@NoArgsConstructor
public class FineExpenseResponseDTO {
    /** Id of Charge. */
    @JsonProperty("charge_id")
    private Long chargeId;

    /** Id of the registered fine. */
    @JsonProperty("fine_id")
    private Long fineId;

    /** Lot ID of the lot to which the fine belongs. */
    @JsonProperty("lot_id")
    private Long lotId;

    /** Period of realization of the fine.   */
    private LocalDateTime period;
    /** Amount of the la multa. */
    private Double amount;
    /** Category of the type of fine (e.g., fixed or percentage). */
    private CategoryChargeDTO category;
}
