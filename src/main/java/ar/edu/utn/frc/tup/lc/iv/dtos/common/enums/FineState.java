package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;

import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;

import java.time.LocalDateTime;

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
    APPROVED_CHALLENGED,

    /**
     * The fine has been assigned an expense.
     */
    IMPUTED_ON_EXPENSE;

    public void validateTransition(FineEntity fineEntity){

        switch (this){
            case APPROVED:
                if (fineEntity.getFineState().equals(FineState.APPROVED)) {
                    throw new IllegalArgumentException("The fine is already approved");
                }
                if(!fineEntity.getFineState().equals(FineState.ON_ASSEMBLY)){
                    throw new IllegalArgumentException("Fine has to be approved by assembly before approving");
                }
                break;
            case APPROVED_CHALLENGED:
                if (fineEntity.getFineState().equals(FineState.APPROVED)
                        && fineEntity.getLastUpdatedAt().plusDays(fineEntity.getSanctionType().getValidityPeriod())
                            .isAfter(LocalDateTime.now())){
                    throw new IllegalArgumentException("User cant no longer appeal the fine");
                }
                break;
            default:
                break;

        }
    }
}
