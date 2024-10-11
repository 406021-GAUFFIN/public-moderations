package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
/**
 * Specification for fine filtering.
 */
public class FineSpecification {


    /**
     * Method to filter by sanction type array.
     * @param  sanctionTypes sanction types to filter
     *
     * @return a specification with filter applied
     */
    public static Specification<FineEntity> inSanctionType(List<Long> sanctionTypes) {
        return (root, query, builder) -> root.join("sanctionType").get("id")
                .in(sanctionTypes);
    }
    /**
     * Method to filter by fine state array.
     * @param  fineStates fine state to filter
     *
     * @return a specification with filter applied
     */
    public static Specification<FineEntity> inModerationState(List<FineState> fineStates) {
        return (root, query, builder) -> root.get("moderationState")
                .in(fineStates);
    }
}
