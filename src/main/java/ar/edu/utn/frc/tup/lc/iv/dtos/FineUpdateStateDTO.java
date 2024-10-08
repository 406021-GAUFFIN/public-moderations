package ar.edu.utn.frc.tup.lc.iv.dtos;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Data Transfer Object for updating the state of a fine.
 */
@Setter
@Getter
@NoArgsConstructor
public class FineUpdateStateDTO {

    /**
     * The ID of the fine to update.
     */
    @NotNull
    private Long id;

    /**
     * The new state to update the fine to.
     */
    @NotNull
    @JsonProperty(namespace = "fine_state")
    private FineState fineState;

    /**
     * The ID of the user who is updating the fine.
     */
    @NotNull
    @JsonProperty(namespace = "updated_by")
    private Integer updatedBy;
}

