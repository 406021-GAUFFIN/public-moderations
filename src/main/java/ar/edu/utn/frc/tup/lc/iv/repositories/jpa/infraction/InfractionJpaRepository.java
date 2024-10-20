package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.infraction;

import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * infraction repository.
 */
public interface InfractionJpaRepository extends JpaRepository<InfractionEntity, Long> {
}
