package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ChargeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Types of sanctions, used in claims, fines and infractions.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctionType extends BaseModel {

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
    private ChargeType chargeType;

    /**
     * The amount to be charged base on the charge type.
     */
    private Double amount;
}



