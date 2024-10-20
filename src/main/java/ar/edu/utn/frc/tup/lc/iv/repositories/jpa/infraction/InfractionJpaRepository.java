package ar.edu.utn.frc.tup.lc.iv.repositories.jpa.infraction;

import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * infraction repository.
 */
public interface InfractionJpaRepository extends JpaRepository<InfractionEntity, Long> {
    /**
     * Get all valid infractions of a plot and type.
     * @param plotId to sarch
     * @param sanctionTypeEntity type to be searched
     * @param cutoffDate date since infractions ar valid for that type
     * @return pending infractions
     */
    @Query("SELECT i FROM InfractionEntity i "
            + "WHERE i.plotId = :plotId "
            + "AND i.sanctionTypeEntity = :sanctionTypeEntity "
            + "AND i.fine IS NULL "
            + "AND i.createdDate >= :cutoffDate")
    List<InfractionEntity> getValidPendingInfractions(
            @Param("plotId") Long plotId,
            @Param("sanctionTypeEntity") SanctionTypeEntity sanctionTypeEntity,
            @Param("cutoffDate") LocalDateTime cutoffDate);
}

