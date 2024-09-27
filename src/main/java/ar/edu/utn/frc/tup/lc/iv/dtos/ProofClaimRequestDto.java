package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for requesting proof claims.
 */

@NoArgsConstructor
@Setter
@Getter
public class ProofClaimRequestDto {

    /**
     * Minimum length for the proof path.
     * Must be at least 5 characters long.
     */
    private static final int PROOF_PATH_MIN_LENGTH = 5;

    /**
     * Maximum length for the proof path.
     * Must not exceed 255 characters.
     */
    private static final int PROOF_PATH_MAX_LENGTH = 255;

    /**
     * Minimum length for the proof text.
     * Must be at least 10 characters long.
     */
    private static final int PROOF_TEXT_MIN_LENGTH = 10;

    /**
     * Maximum length for the proof text.
     * Must not exceed 500 characters.
     */
    private static final int PROOF_TEXT_MAX_LENGTH = 500;

    /** Path to the proof file (5-255 characters). */
    @JsonProperty("proof_path")
    @NotBlank(message = "Proof path is required and cannot be empty.")
    @Size(min = PROOF_PATH_MIN_LENGTH, max = PROOF_PATH_MAX_LENGTH, message = "Proof path must be between 5 and 255 characters.")
    private String proofPath;

    /** Type of the proof, must not be null. */
    @JsonProperty("proof_type")
    @NotNull(message = "Proof type is required.")
    private ProofType proofType;

    /** Description of the proof (10-500 characters). */
    @JsonProperty("proof_text")
    @Size(min = PROOF_TEXT_MIN_LENGTH, max = PROOF_TEXT_MAX_LENGTH, message = "Proof text must be between 10 and 500 characters.")
    private String proofText;

    /** ID of the associated claim, must not be null. */
    @JsonProperty("claim_id")
    @NotNull(message = "Claim ID is required.")
    private Long claimId;

    /** ID of the person who created the proof, must not be null. */
    @JsonProperty("created_by")
    @NotNull(message = "ID of the person who created proof is required.")
    private Long createdBy;
}
