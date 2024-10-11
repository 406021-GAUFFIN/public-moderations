package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.entities.claim.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Claim repository.
 */
@Repository
public interface ClaimJpaRepository extends JpaRepository<ClaimEntity, Long> {
}
