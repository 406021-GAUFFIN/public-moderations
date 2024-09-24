package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import lombok.*;

import java.util.List;

/*
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MatchRps.class)
})*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fine extends BaseModel{


    /**
     * The ID of the plot associated with the moderation process.
     */
    private Integer plotId;


    /**
     * The current state of the moderation
     * process, represented by {@link FineState}.
     */
    private FineState fineState;

    /**
     * The type of sanction linked to the moderation process.
     */
    private SanctionType sanctionType;


    private List<Infraction> infractions;




}
