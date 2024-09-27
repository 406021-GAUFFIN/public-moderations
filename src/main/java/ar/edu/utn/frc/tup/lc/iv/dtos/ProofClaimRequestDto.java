package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofContext;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ProofClaimRequestDto {

    /**
     * The path to the proof file or document.
     * Cannot be null or empty.
     * Must be between 5 and 255 characters.
     */
    @JsonProperty("proof_path")
    @NotBlank(message = "Proof path is required and cannot be empty.")
    @Size(min = 5, max = 255, message = "Proof path must be between 5 and 255 characters.")
    private String proofPath;

    /**
     * The type of the proof, represented by {@link ProofType}.
     * Cannot be null.
     */
    @JsonProperty("proof_type")
    @NotNull(message = "Proof type is required.")
    private ProofType proofType;


    /**
     * Textual content or description of the proof.
     * Must be between 10 and 500 characters.
     */
    @JsonProperty("proof_text")
    @Size(min = 10, max = 500, message = "Proof text must be between 10 and 500 characters.")
    private String proofText;

    /**
     * The claim ID associated with this proof.
     * Cannot be null.
     */
    @JsonProperty("claim_id")
    @NotNull(message = "Claim ID is required.")
    private Long claimId;

    @JsonProperty("created_by")
    @NotNull(message = "id of person who created proof is necessary")
    private Long createdBy;


}
