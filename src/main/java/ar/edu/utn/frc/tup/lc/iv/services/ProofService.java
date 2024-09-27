package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.ProofClaimRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.ProofResponseDto;
import org.springframework.stereotype.Service;


@Service
public interface ProofService {

    ProofResponseDto createClaimProof(ProofClaimRequestDto  requestDto);
}
