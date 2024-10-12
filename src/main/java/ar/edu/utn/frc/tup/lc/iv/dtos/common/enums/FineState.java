package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;

import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;



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
    REJECTED;

    /**
     * Validates the state transition of a fine.
     * Throws an exception if the transition is not allowed
     * based on the current fine state.
     *
     * @param fineEntity the fine entity whose state is being updated
     * @throws IllegalArgumentException if the state transition is invalid
     */
    public void validateTransition(FineEntity fineEntity) {

        if (this == FineState.APPROVED) {
            if (fineEntity.getFineState().equals(FineState.APPROVED)) {
                throw new IllegalArgumentException("The fine is already approved");
            }
            if (!fineEntity.getFineState().equals(FineState.ON_ASSEMBLY)) {
                throw new IllegalArgumentException("Fine has to be approved by assembly before approving");
            }
        }
    }
}
