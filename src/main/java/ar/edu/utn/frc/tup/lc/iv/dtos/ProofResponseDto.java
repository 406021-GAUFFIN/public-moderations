package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofContext;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for retrieving proof claims.
 */
@NoArgsConstructor
@Setter
@Getter
public class ProofResponseDto {


    /** The path to the proof file (5-255 characters). */
    @JsonProperty("proof_path")
    private String proofPath;

    /** Type of the proof, must not be null. */
    @JsonProperty("proof_type")
    private ProofType proofType;

    /** Context in which the proof is provided, must not be null. */
    @JsonProperty("proof_context")
    private ProofContext proofContext;

    /** Description of the proof (10-500 characters). */
    @JsonProperty("proof_text")
    private String proofText;
}
