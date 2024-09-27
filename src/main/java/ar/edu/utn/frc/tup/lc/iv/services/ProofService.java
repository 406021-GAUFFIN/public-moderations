package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.ProofClaimRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.ProofResponseDto;
import org.springframework.stereotype.Service;

/**
 * Service to manage proofs.
 */
@Service
public interface ProofService {

    /**
     * Creates a proof claim based on the provided request data.
     *
     * @param requestDto the details of the proof claim to be created.
     * @return the response containing details of the created proof claim.
     */
    ProofResponseDto createClaimProof(ProofClaimRequestDto  requestDto);
}
