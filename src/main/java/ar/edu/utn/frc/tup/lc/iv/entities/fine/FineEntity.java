package ar.edu.utn.frc.tup.lc.iv.entities.fine;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import jakarta.persistence.*;
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
     * The ID of the plot associated with the moderation process.
     */
    @Column(name = "PLOT_ID")
    private Integer plotId;



    /**
     * The current state of the moderation
     * process, represented by {@link FineState}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "FINE_STATE")
    private FineState fineState;

    /**
     * The type of sanction linked to the moderation process.
     */
    @ManyToOne
    @JoinColumn(name = "SANCTION_TYPE", referencedColumnName = "id")
    private SanctionTypeEntity sanctionType;

    /**
     * The list of infractions associated
     * with the moderation process.
     */
    @OneToMany(mappedBy = "fine",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InfractionEntity> infractions;

}
