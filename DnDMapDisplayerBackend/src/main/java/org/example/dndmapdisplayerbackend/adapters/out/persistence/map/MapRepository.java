package org.example.dndmapdisplayerbackend.adapters.out.persistence.map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MapRepository extends JpaRepository<MapEntity, Long> {
    @Modifying
    @Query("delete from MapEntity m where m.campaignId = :campaignId")
    void deleteAllByCampaignId(@Param("campaignId") Long campaignId);
}
