package org.example.dndmapdisplayerbackend.domain.port.in.campaign;

import org.example.dndmapdisplayerbackend.domain.model.Campaign;

public interface CreateCampaignUseCase {
    Campaign createCampaign( String name, String description, String email);

}
