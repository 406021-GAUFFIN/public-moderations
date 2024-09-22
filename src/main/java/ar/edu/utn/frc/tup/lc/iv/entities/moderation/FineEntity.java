package ar.edu.utn.frc.tup.lc.iv.entities.moderation;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ModerationState;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Entity representing a moderation process in the system.
 * Moderation includes details about
 * the process, its state, and related infractions.
 */

@Entity
@Table(name = FineEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class FineEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "FINE";

    /**
     * The ID of the user associated with the moderation process.
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * A description of the moderation process.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Indicates whether the moderation
     * was approved by the assembly.
     */
    @Column(name = "APPROVED_BY_ASSEMBLY")
    private Boolean approvedByAssembly;

    /**
     * The price associated with the moderation process.
     */
    @Column(name = "PRICE")
    private Double price;

    /**
     * The current state of the moderation
     * process, represented by {@link ModerationState}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "MODERATION_STATE")
    private ModerationState moderationState;

    /**
     * The type of sanction linked to the moderation process.
     */
    @OneToOne
    @JoinColumn(name = "SANCTION_TYPE", referencedColumnName = "id")
    private SanctionTypeEntity sanctionTypeEntity;

    /**
     * The list of infractions associated
     * with the moderation process.
     */
    @OneToMany(mappedBy = "moderation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InfractionEntity> infractions;

}
