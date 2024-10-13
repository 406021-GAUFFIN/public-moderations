package ar.edu.utn.frc.tup.lc.iv.dtos.external;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PlotDTO {
    /** Primary key of the plot. */
    private Long id;

    /** number of the plot.
     */
    @JsonProperty("plot_number")
    private Long plotId;

}
