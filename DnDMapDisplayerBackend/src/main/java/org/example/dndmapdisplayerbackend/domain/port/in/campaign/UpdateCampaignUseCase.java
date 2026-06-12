package org.example.dndmapdisplayerbackend.domain.port.in.campaign;

import org.example.dndmapdisplayerbackend.domain.model.Campaign;

public interface UpdateCampaignUseCase {
    Campaign updateCampaign(Long id, String name, String description, String email);
}
