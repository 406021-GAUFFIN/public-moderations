package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Fine repository.
 */
@Repository
public interface SanctionTypeJpaRepository extends JpaRepository<SanctionTypeEntity, Long> {



}
