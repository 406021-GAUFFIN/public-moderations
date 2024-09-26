package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;

/**
 * Enum representing the various states of a fine process in the system.
 */
public enum FineState {
    /**
     * The fine is currently being reviewed by the assembly.
     */
    ON_ASSEMBLY(1),

    /**
     * The fine has been approved.
     */
    APPROVED(2),

    /**
     * The fine has been rejected.
     */
    REJECTED(3),
    /**
     * The fine has been approved and the challenged by the owner of the plot
     */
    APPROVED_CHALENGED(4),
    /**
     * The fine has been assigned an expense.
     */
    IMPUTED_ON_EXPENSE(5);

    private final int id;
    FineState(int id) {
        this.id  = id ;
    }

    public int getId() {
        return id;
    }
}
