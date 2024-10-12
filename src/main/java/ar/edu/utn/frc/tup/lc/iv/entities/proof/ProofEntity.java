package ar.edu.utn.frc.tup.lc.iv.entities.proof;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofContext;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ProofType;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.claim.ClaimEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;


/**
 * Entity representing a proof related to a claim or infraction.
 * This includes information about
 * the proof's path, type, context, and text.
 */
@Entity
@Setter
@Getter
@Table(name = ProofEntity.TABLE_NAME)
@NoArgsConstructor
@Audited
public class ProofEntity extends BaseEntity {
    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "PROOF";

    /**
     * The path to the proof file or document.
     */
    @Column(name = "PROOF_PATH")
    private String proofPath;

    /**
     * The type of the proof, represented by {@link ProofType}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "PROOF_TYPE")
    private ProofType proofType;

    /**
     * The context in which the proof is
     * provided, represented by {@link ProofContext}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "PROOF_CONTEXT")
    private ProofContext proofContext;

    /**
     * Textual content or description of the proof.
     */
    @Column(name = "PROOF_TEXT")
    private String proofText;

    /**
     * The claim associated with this proof.
     */
    @ManyToOne
    @JoinColumn(name = "CLAIM_ID")
    private ClaimEntity claim;

    /**
     * The infraction associated with this proof.
     */
    @ManyToOne
    @JoinColumn(name = "INFRACTION")
    private InfractionEntity infraction;

}
