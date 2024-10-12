package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.ProofClaimRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.ProofResponseDto;
import ar.edu.utn.frc.tup.lc.iv.services.ProofService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ProofControllerTest {

    @InjectMocks
    private ProofController proofController;

    @Mock
    private ProofService proofService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProofForClaimSuccess() {

        ProofClaimRequestDto requestDto = new ProofClaimRequestDto();
        ProofResponseDto responseDto = new ProofResponseDto();

        when(proofService.createClaimProof(requestDto)).thenReturn(responseDto);

        ResponseEntity<ProofResponseDto> response = proofController.createProofForClaim(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    public void testCreateProofForClaimInvalidRequest() {

        ProofClaimRequestDto requestDto = new ProofClaimRequestDto();
        when(proofService.createClaimProof(requestDto)).thenThrow(new IllegalArgumentException("Invalid proof request"));


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            proofController.createProofForClaim(requestDto);
        });
        assertEquals("Invalid proof request", exception.getMessage());
    }

}
