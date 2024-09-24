package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class FineSpecification {
    private FineSpecification() {}


    public static Specification<FineEntity> price(Double price) {
        return (root, query, builder) -> builder.equal(root.get("price"), price);
    }


    public static Specification<FineEntity> inSanctionType(List<Long> sanctionTypes) {
        return (root, query, builder) -> root.join("sanctionType").get("id")
                .in(sanctionTypes);
    }

    public static Specification<FineEntity> inModerationState(List<FineState> fineStates) {
        return (root, query, builder) -> root.get("moderationState")
                .in(fineStates);
    }
}
