package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType;

import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Fine repository.
 */
@Repository
public interface SanctionTypeJpaRepository extends JpaRepository<SanctionTypeEntity, Long>, JpaSpecificationExecutor<SanctionTypeEntity> {

    /**
     * Get all sanction types paginated band by filters.
     *
     * @param filter the filter specification
     * @param  pageable the Pageable object indicatins pagesize and current page
     * @return sanction types
     */
    @Override
    Page<SanctionTypeEntity> findAll(Specification<SanctionTypeEntity> filter, Pageable pageable);

    /**
     * Get all fines  band by filters.
     *
     * @param filter the filter specification
     * @return all sanction types.
     */
    @Override
    List<SanctionTypeEntity> findAll(Specification<SanctionTypeEntity> filter);

}
