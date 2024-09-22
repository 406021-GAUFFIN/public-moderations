package ar.edu.utn.frc.tup.lc.iv.repositories.jpa;

import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FineJpaRepository extends JpaRepository<FineEntity, Long> {



    Page<FineEntity> findAll (Pageable pageable);

}
