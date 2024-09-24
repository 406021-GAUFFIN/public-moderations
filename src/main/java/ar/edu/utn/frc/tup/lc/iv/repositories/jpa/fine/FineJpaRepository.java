package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;

import ar.edu.utn.frc.tup.lc.iv.models.Fine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineJpaRepository extends JpaRepository<FineEntity, Long> , JpaSpecificationExecutor<FineEntity> {



    Page<FineEntity> findAll (Pageable pageable);
    Page<FineEntity> findAll (Specification<FineEntity> filter,Pageable pageable);
    List<FineEntity> findAll (Specification<FineEntity> filter);

}
