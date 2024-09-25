package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.SanctionSeverity;

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
     * The severity of the sanction, represented
     * by {@link SanctionSeverity}.
     */
    private SanctionSeverity sanctionSeverity;
}
