package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
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
public interface FineJpaRepository extends JpaRepository<FineEntity, Long>, JpaSpecificationExecutor<FineEntity> {

    /**
     * Get all fines paginated band by filters.
     *
     * @param filter the filter specification
     * @param  pageable the Pageable object indicatins pagesize and current page
     * @return paginated fines.
     */
    @Override
    Page<FineEntity> findAll(Specification<FineEntity> filter, Pageable pageable);

    List<FineEntity> findAllByFineState(FineState state);


}
