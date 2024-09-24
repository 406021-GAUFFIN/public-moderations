package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ModerationState;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.models.Fine;
import ar.edu.utn.frc.tup.lc.iv.models.SanctionType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class FineSpecification {
//    @RequestParam(required = false) List<ModerationState> moderationState,
//    @RequestParam(required = false) List<SanctionType> sanctionType,
//    @RequestParam(required = false) Double price
    private FineSpecification() {}


    public static Specification<FineEntity> price(Double price) {
        return (root, query, builder) -> builder.equal(root.get("price"), price);
    }

//
//    public static Specification<Hospital> hasDoctorInSpeciality(String speciality) {
//        return (root, query, builder) -> {
//            Join<Doctor,Hospital> hospitalDoctors = root.join("doctors");
//            return builder.equal(hospitalDoctors.get("speciality"), speciality);
//        };
//    }

    public static Specification<FineEntity> inModerationState(List<ModerationState> moderationStates) {
        return (root, query, builder) -> root.get("moderationState")
                .in(moderationStates);
    }
}
