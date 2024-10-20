package ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ChargeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entity representing a moderation process in the system.
 * Moderation includes details about
 * the process, its state, and related infractions.
 */
@NoArgsConstructor
@Getter
@Setter
public class CreateSanctionTypeDTO {


    /**
     * Id of the user creating the sanction type.
     */
    @JsonProperty("user_id")
    private Long userId;

    /**
     * The name of the sanction type.
     */
    private String name;

    /**
     * A brief description of the sanction type.
     */
    private String description;


    /**
     * A price type associated with the fine.
     */
    @JsonProperty("charge_type")
    private ChargeType chargeType;

    /**
     * The amount to be charged base on the charge type.
     */
    private Double amount;

    /**
     * Amounts of days for the infraction to expire.
     */
    @JsonProperty("infraction_days_to_expire")
    private Integer infractionDaysToExpire;

    /**
     * Amounts of infractions for a fine to be created.
     */
    @JsonProperty("amount_of_infractions_for_fine")
    private Integer amountOfInfractionsForFine;


}

