package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;

/**
 * Enum representing the various states of a fine process in the system.
 */
public enum FineState {
    /**
     * The fine is currently being reviewed by the assembly.
     */
    ON_ASSEMBLY,

    /**
     * The fine has been approved.
     */
    APPROVED,

    /**
     * The fine has been rejected.
     */
    REJECTED,
    /**
     * The fine has been approved and the challenged by the owner of the plot.
     */
    APPROVED_CHALENGED,
    /**
     * The fine has been assigned an expense.
     */
    IMPUTED_ON_EXPENSE;


}
