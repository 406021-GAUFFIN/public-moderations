package ar.edu.utn.frc.tup.lc.iv.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.time.LocalDateTime;


/**
 * Base model class providing common fields
 * and behavior for all models.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {


    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdDate;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastUpdatedAt;

    private String lastUpdatedBy;


}
