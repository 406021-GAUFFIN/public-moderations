package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.dtos.ProofClaimRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.ProofResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ClaimState;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofContext;
import ar.edu.utn.frc.tup.lc.iv.entities.claim.ClaimEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.proof.ProofEntity;
import ar.edu.utn.frc.tup.lc.iv.error.InvalidClaimStateException;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.claim.ClaimJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.claim.ProofJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.ProofService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ProofService for managing proof claims.
 */
@Service
@RequiredArgsConstructor
public class ProofServiceImpl implements ProofService {

    /**
     * Repository for accessing proof entities.
     */
    private final ProofJpaRepository proofJpaRepository;

    /**
     * Repository for accessing claim entities.
     */
    private final ClaimJpaRepository claimJpaRepository;

    /**
     * Model mapper for converting between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Creates a proof claim from the request data; throws
     * exceptions for invalid states or missing claims.
     *
     * @param requestDto details of the proof claim to create.
     * @return response with the created proof claim details.
     */

    @Override
    @Transactional
    public ProofResponseDto createClaimProof(ProofClaimRequestDto requestDto) {
        ClaimEntity claimEntity = claimJpaRepository.findById(requestDto.getClaimId())
                .orElseThrow(() -> new EntityNotFoundException("Claim not found"));
        if (!isValidClaimState(claimEntity.getClaimState())) {
            throw new InvalidClaimStateException("The claim state is not valid for adding proof.");
        }
        ProofEntity proofEntity = modelMapper.map(requestDto, ProofEntity.class);
        proofEntity.setClaim(claimEntity);
        proofEntity.setCreatedBy(requestDto.getCreatedBy());
        proofEntity.setProofContext(ProofContext.CLAIM_JUSTIFICATION);
        return modelMapper.map(proofJpaRepository.save(proofEntity), ProofResponseDto.class);
    }

    /**
     * Checks if the claim state is valid for adding proof.
     *
     * @param claimState the state of the claim to check.
     * @return true if the claim state is valid; false otherwise.
     */

    private Boolean isValidClaimState(ClaimState claimState) {
        return claimState == ClaimState.SENT || claimState == ClaimState.ON_REVISION;
    }
}
