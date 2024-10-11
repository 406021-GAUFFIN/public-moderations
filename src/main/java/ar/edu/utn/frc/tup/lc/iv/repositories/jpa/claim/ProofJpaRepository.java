package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.claim;

import ar.edu.utn.frc.tup.lc.iv.entities.proof.ProofEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Proof repository.
 */
@Repository
public interface ProofJpaRepository extends JpaRepository<ProofEntity, Long> {
}
