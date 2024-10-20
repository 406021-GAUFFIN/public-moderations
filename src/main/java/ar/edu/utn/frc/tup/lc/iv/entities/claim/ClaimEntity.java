package ar.edu.utn.frc.tup.lc.iv.entities.claim;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ClaimState;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.proof.ProofEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;


import java.util.List;


/**
 * Entity representing a claim made by a user.
 * A claim can be linked to a sanction type and an
 * infraction, with an associated state.
 */
@Entity
@Table(name = ClaimEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@Audited
public class ClaimEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "CLAIM";


    /**
     * The ID of the plot who is sent the claim.
     */
    @Column(name = "PLOT_ID")
    private Integer plotId;

    /**
     * The type of sanction related to the claim.
     */
    @ManyToOne
    @JoinColumn(name = "SANCTION_TYPE", referencedColumnName = "id")
    private SanctionTypeEntity sanctionTypeEntity;

    /**
     * The current state of the claim, represented by {@link ClaimState}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "CLAIM_STATE")
    private ClaimState claimState;

    /**
     * The infraction associated with the claim.
     */
    @ManyToOne
    @JoinColumn(name = "INFRACTION_ID")
    private InfractionEntity infraction;


    /**
     * The list of proofs associated with the claim.
     */
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private List<ProofEntity> proofs;

}
