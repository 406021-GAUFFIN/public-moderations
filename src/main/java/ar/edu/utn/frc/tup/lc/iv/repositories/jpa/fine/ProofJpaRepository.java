package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine;

import ar.edu.utn.frc.tup.lc.iv.entities.proof.ProofEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProofJpaRepository extends JpaRepository<ProofEntity,Long> {
}
