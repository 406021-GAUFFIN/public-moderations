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
public class ProofResponseDto {

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
     * The context in which the proof is provided, represented
     * by {@link ProofContext}.
     * Cannot be null.
     */
    @JsonProperty("proof_context")
    @NotNull(message = "Proof context is required.")
    private ProofContext proofContext;

    /**
     * Textual content or description of the proof.
     * Must be between 10 and 500 characters.
     */
    @JsonProperty("proof_text")
    @Size(min = 10, max = 500, message = "Proof text must be between 10 and 500 characters.")
    private String proofText;
}
