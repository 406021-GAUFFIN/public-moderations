package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.ProofClaimRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.ProofResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.services.ProofService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing proof operations.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/proof")
@CrossOrigin(origins = "*")
public class ProofController {

    /**
     * ProofService dependency.
     */
    private final ProofService proofService;

    /**
     * Creates a proof for an existing claim based on the provided request DTO.
     *
     * @param proofClaimRequestDto DTO containing proof details and the claim ID.
     * @return The response DTO of the created proof for the claim.
     */
    @Operation(
            summary = "Create a proof for a claim",
            description = "Creates a new proof for an existing claim by providing details such as proof path, proof type, and claim ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Proof for claim created successfully",
                    content = @Content(
                            schema = @Schema(implementation = ProofResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid proof request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Claim not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @PostMapping("/claim")
    public ResponseEntity<ProofResponseDto> createProofForClaim(
            @RequestBody @Valid ProofClaimRequestDto proofClaimRequestDto) {
        return ResponseEntity.ok(proofService.createClaimProof(proofClaimRequestDto));
    }
}
