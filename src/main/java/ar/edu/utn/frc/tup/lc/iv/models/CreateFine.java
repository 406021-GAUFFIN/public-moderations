package ar.edu.utn.frc.tup.lc.iv.models;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;

import java.util.List;

public class CreateFine {


    /**
     * The ID of the plot associated with the moderation process.
     */
    public Integer plotId;


    /**
     * The current state of the moderation
     * process, represented by {@link FineState}.
     */
    public int fineState;

    /**
     * The type of sanction linked to the moderation process.
     */
    public int idSanctionType;


}
