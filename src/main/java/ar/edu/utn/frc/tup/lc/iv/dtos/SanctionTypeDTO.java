package ar.edu.utn.frc.tup.lc.iv.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entity representing a moderation process in the system.
 * Moderation includes details about
 * the process, its state, and related infractions.
 */
@NoArgsConstructor
@Getter
@Setter
public class SanctionTypeDTO  {


    /**
     * The name of the sanction type.
     */
    private String name;

    /**
     * A brief description of the sanction type.
     */
    private String description;






    }

