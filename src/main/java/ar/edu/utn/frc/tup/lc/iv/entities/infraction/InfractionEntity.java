package ar.edu.utn.frc.tup.lc.iv.entities.infraction;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.claim.ClaimEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.moderation.ModerationEntity;
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

import java.util.List;

/**
 * Entity representing an infraction in the system.
 * An infraction is associated with a user, a description,
 * a sanction type, a moderation process, and multiple claims.
 */
@Entity
@Table(name = InfractionEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class InfractionEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "INFRACTION";

    /**
     * The ID of the user who committed the infraction.
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * A description of the infraction.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * The current state of the infraction
     * represented by {@link InfractionState}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "INFRACTION_STATE")
    private InfractionState infractionState;

    /**
     * The type of sanction associated with the infraction.
     */
    @OneToOne
    @JoinColumn(name = "SANCTION_TYPE", referencedColumnName = "id")
    private SanctionTypeEntity sanctionTypeEntity;

    /**
     * The list of claims related to the infraction.
     */
    @OneToMany(mappedBy = "infraction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClaimEntity> claims;

    /**
     * The moderation process linked to the infraction.
     */
    @ManyToOne
    @JoinColumn(name = "MODERATION_ID", referencedColumnName = "id")
    private ModerationEntity moderation;


}
