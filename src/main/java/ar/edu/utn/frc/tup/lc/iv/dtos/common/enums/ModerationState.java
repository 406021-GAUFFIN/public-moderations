package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;

/**
 * Enum representing the various states of a moderation process in the system.
 */
public enum ModerationState {
    /**
     * The moderation is currently being reviewed by the assembly.
     */
    ON_ASSEMBLY,

    /**
     * The moderation has been approved.
     */
    APPROVED,

    /**
     * The moderation has been rejected.
     */
    REJECTED,

    /**
     * The moderation has been assigned an expense.
     */
    IMPUTED_ON_EXPENSE
}
