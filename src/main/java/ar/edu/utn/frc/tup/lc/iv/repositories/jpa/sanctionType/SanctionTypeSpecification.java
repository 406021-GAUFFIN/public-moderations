package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType;

import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;


/**
 * Specification for fine filtering.
 */
public class SanctionTypeSpecification {


    /**
     * Method to filter by sanction type array.
     * @param  partialName sanction types to filter
     *
     * @return a specification with filter applied
     */
    public static Specification<SanctionTypeEntity> partialName(String partialName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + partialName.toLowerCase(Locale.ROOT) + "%"
                );
    }



}
