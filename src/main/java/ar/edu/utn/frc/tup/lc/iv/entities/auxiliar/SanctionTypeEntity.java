package ar.edu.utn.frc.tup.lc.iv.entities.auxiliar;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.SanctionSeverity;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing types of sanctions in the system.
 * Each sanction type has a name,description, and
 * severity level.
 */
@Entity
@Table(name = SanctionTypeEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class SanctionTypeEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "SANCTION_TYPE";

    /**
     * The name of the sanction type.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * A brief description of the sanction type.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * The severity of the sanction, represented
     * by {@link SanctionSeverity}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "SANCTION_SEVERITY")
    private SanctionSeverity sanctionSeverity;

}
