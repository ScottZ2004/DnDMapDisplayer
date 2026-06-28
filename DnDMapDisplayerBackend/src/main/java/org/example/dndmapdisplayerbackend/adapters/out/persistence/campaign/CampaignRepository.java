package org.example.dndmapdisplayerbackend.adapters.out.persistence.campaign;

import org.example.dndmapdisplayerbackend.adapters.out.persistence.user.UserEntity;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {

    List<CampaignEntity> getCampaignEntitiesByUser(UserEntity user);

}
