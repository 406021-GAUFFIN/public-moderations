package ar.edu.utn.frc.tup.lc.iv.dtos.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents a category for charges, including its unique identifier,
 * name, and description.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryChargeDTO {
    /** Primary key of the category charge. */
    private Long categoryChargeId;

    /** Name of the category charge (e.g., Service, Tax). */
    private String name;

    /** Description of the category charge. */
    private String description;
}
