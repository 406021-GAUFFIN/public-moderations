package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ClaimState;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Entity representing a claim made by a user.
 * A claim can be linked to a sanction type and an
 * infraction, with an associated state.
 */
@NoArgsConstructor
@Getter
@Setter
public class ClaimDTO extends BaseDTO {

    /**
     * Name of the table on the database.
     */
    private Long userId;

    /**
     * The date of expiration for a claim.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date expiringDate;

    /**
     * The type of sanction related to the claim.
     */
    private SanctionTypeDTO sanctionType;

    /**
     * The current state of the claim, represented by {@link ClaimState}.
     */
    private ClaimState claimState;

    /**
     * The infraction id associated with the claim.
     */
    private Long infractionId;
}
