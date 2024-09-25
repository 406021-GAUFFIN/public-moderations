package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

/**
 * Fine made by multiple infractions.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fine extends BaseModel {


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

    /**
     * Infractions that compose the fine.
     */
    private List<Infraction> infractions;




}
