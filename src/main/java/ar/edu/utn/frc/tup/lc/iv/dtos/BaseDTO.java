package ar.edu.utn.frc.tup.lc.iv.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Base DTO class providing common fields
 * and behavior for all entities.
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseDTO {

    /**
     * Unique identifier for the entity.
     */
    private Long id;

    /**
     * Date and time when the entity was created.
     */
    @JsonProperty("created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdDate;

    /**
     * User id of the person who created the entity.
     */
    @JsonProperty("created_by")
    private Long createdBy;

    /**
     * Date and time when the entity was last updated.
     */
    @JsonProperty("last_updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastUpdatedAt;

    /**
     * Use id of the person who last updated the entity.
     */
    @JsonProperty("last_updated_by")
    private Long lastUpdatedBy;
}
