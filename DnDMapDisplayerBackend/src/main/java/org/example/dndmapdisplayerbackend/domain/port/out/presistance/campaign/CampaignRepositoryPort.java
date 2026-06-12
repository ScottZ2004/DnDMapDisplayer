package org.example.dndmapdisplayerbackend.domain.port.out.presistance.campaign;

import org.example.dndmapdisplayerbackend.domain.model.Campaign;
import org.example.dndmapdisplayerbackend.domain.model.User;

import java.util.List;

public interface CampaignRepositoryPort {
    Campaign getCampaign(Long id);

    List<Campaign> getAllCampaignsFromUser(User user);

    Campaign save(Campaign campaign, User user);

    void delete(Long id);

    Campaign update(Campaign campaign);

    boolean userHasCampaign(Long id, User user);
}
