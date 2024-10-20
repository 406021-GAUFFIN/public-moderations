package ar.edu.utn.frc.tup.lc.iv.entities.auxiliar;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ChargeType;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

/**
 * Entity representing types of sanctions in the system.
 */
@Entity
@Table(name = SanctionTypeEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@Audited
public class SanctionTypeEntity extends BaseEntity {

    /**
     * Name of the table in the database.
     */
    static final String TABLE_NAME = "SANCTION_TYPE";

    /**
     * The name of the sanction type (e.g., "Late Payment").
     */
    @Column(name = "NAME")
    private String name;

    /**
     * A brief description of the sanction type, explaining its nature or purpose.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * A price type associated with the fine.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "CHARGE_TYPE")
    private ChargeType chargeType;

    /**
     * The amount to be charged base on the charge type.
     */
    @Column(name = "AMOUNT")
    private Double amount;

    /**
     * Amounts of days for the infraction to expire.
     */
    @Column(name = "INFRACTION_DAYS_TO_EXPIRE")
    private Integer infractionDaysToExpire;

    /**
     * Amounts of infractions for a fine to be created.
     */
    @Column(name = "AMOUNT_OF_INFRACTION_FOR_FINE")
    private Integer amountOfInfractionsForFine;
}
