package ar.edu.utn.frc.tup.lc.iv.services;


import ar.edu.utn.frc.tup.lc.iv.dtos.ProofClaimRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.ProofResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ClaimState;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofType;
import ar.edu.utn.frc.tup.lc.iv.entities.claim.ClaimEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.proof.ProofEntity;
import ar.edu.utn.frc.tup.lc.iv.error.InvalidClaimStateException;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.claim.ClaimJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.claim.ProofJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.impl.ProofServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProofServiceTests {


    @Mock
    private ClaimJpaRepository claimJpaRepository;

    @Mock
    private ProofJpaRepository proofJpaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProofServiceImpl proofService;

    @Test
    public void createClaimProofTestSuccess(){
        ProofClaimRequestDto requestDto = new ProofClaimRequestDto();
        requestDto.setClaimId(1L);
        requestDto.setCreatedBy(1L);
        requestDto.setProofPath("test/path");
        requestDto.setProofType(ProofType.DOCUMENT);
        requestDto.setProofText("This is a test proof.");

        ClaimEntity claimEntity = new ClaimEntity();
        claimEntity.setClaimState(ClaimState.SENT);

        ProofEntity proofEntity = new ProofEntity();

        when(claimJpaRepository.findById(1L)).thenReturn(Optional.of(claimEntity));
        when(modelMapper.map(requestDto, ProofEntity.class)).thenReturn(proofEntity);
        when(proofJpaRepository.save(proofEntity)).thenReturn(proofEntity);
        when(modelMapper.map(proofEntity, ProofResponseDto.class)).thenReturn(new ProofResponseDto());


        ProofResponseDto response = proofService.createClaimProof(requestDto);


        assertNotNull(response);
        verify(claimJpaRepository, times(1)).findById(1L);
        verify(proofJpaRepository, times(1)).save(proofEntity);
    }

    @Test
    public void testCreateClaimProof_ClaimNotFound() {

        ProofClaimRequestDto requestDto = new ProofClaimRequestDto();
        requestDto.setClaimId(1L);

        when(claimJpaRepository.findById(1L)).thenReturn(Optional.empty());


        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                proofService.createClaimProof(requestDto)
        );

        assertEquals("Claim not found", exception.getMessage());
        verify(claimJpaRepository, times(1)).findById(1L);
        verify(proofJpaRepository, never()).save(any(ProofEntity.class));
    }

    @Test
    public void testCreateClaimProof_InvalidClaimState() {

        ProofClaimRequestDto requestDto = new ProofClaimRequestDto();
        requestDto.setClaimId(1L);

        ClaimEntity claimEntity = new ClaimEntity();
        claimEntity.setClaimState(ClaimState.APPROVED);

        when(claimJpaRepository.findById(1L)).thenReturn(Optional.of(claimEntity));


        InvalidClaimStateException exception = assertThrows(InvalidClaimStateException.class, () ->
                proofService.createClaimProof(requestDto)
        );

        assertEquals("The claim state is not valid for adding proof.", exception.getMessage());
        verify(claimJpaRepository, times(1)).findById(1L);
        verify(proofJpaRepository, never()).save(any(ProofEntity.class));
    }
}
