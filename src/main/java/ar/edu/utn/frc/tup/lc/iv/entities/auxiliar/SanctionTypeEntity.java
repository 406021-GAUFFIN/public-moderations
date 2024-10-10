package ar.edu.utn.frc.tup.lc.iv.entities.auxiliar;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing types of sanctions in the system.
 */
@Entity
@Table(name = SanctionTypeEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
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
    @Column(name = "PRICE_TYPE")
    private String priceType;

    /**
     * The monetary price associated with the sanction.
     */
    @Column(name = "PRICE")
    private Double price;
}
