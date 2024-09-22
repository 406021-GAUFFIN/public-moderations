package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ModerationState;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/*
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MatchRps.class)
})*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fine extends BaseModel{


    /**
     * The ID of the user associated with the moderation process.
     */
    private Integer userId;

    /**
     * A description of the moderation process.
     */
    private String description;


    /**
     * The price associated with the moderation process.
     */
    private Double price;

    /**
     * The current state of the moderation
     * process, represented by {@link ModerationState}.
     */
    private ModerationState moderationState;

    /**
     * The type of sanction linked to the moderation process.
     */
    private SanctionType sanctionType;


    private List<Infraction> infractions;




}
