package ar.edu.utn.frc.tup.lc.iv.dtos;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

@Setter
@Getter
@NoArgsConstructor
public class FineUpdateStateDTO {

    @NotNull
    private Long id;

    @NotNull
    @JsonProperty(namespace = "fine_state")
    private FineState fineState;

    @NotNull
    @JsonProperty(namespace = "updated_by")
    private Integer updatedBy;
}
