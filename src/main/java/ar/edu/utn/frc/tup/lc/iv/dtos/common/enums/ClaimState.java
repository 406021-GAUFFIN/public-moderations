package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;

/**
 * Enum representing the various states of a claim in the system.
 */
public enum ClaimState {
    /**
     * The claim has been sent and is awaiting review.
     */
    SENT,

    /**
     * The claim is currently under review.
     */
    ON_REVISION,

    /**
     * The claim has been appealed.
     */
    APPEALED,

    /**
     * The claim has been approved.
     */
    APPROVED,

    /**
     * The claim has been rejected.
     */
    REJECTED
}
