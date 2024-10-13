package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;


import java.util.List;


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
     * @param fineState the fine entity whose state is being updated
     * @throws IllegalArgumentException if the state transition is invalid
     */
    public void validateTransition(FineState fineState) {

        if (!possibleNextFineStates().contains(fineState)) {

                throw new IllegalArgumentException("The fine cant change to this state");

        }
    }

    /**
     *Returns to what possible states can a fine change.

     * @return  List of possible next states if the state transition is invalid
     */
    public List<FineState> possibleNextFineStates() {
        if (this == FineState.ON_ASSEMBLY) {
            return List.of(APPROVED, REJECTED);
        } else {
            return List.of();
        }
    }
}
