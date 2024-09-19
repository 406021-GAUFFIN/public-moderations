package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;

/**
 * Enum representing the various states of an infraction in the system.
 */
public enum InfractionState {
    /**
     * The infraction has been approved.
     */
    APPROVED,

    /**
     * The infraction is currently under appeal.
     */
    ON_APPEALING,

    /**
     * The infraction has been appealed.
     */
    APPEALED,

    /**
     * The infraction has been rejected.
     */
    REJECTED
}
