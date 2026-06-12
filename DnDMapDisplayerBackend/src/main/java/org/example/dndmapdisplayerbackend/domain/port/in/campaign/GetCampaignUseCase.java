package org.example.dndmapdisplayerbackend.domain.port.in.campaign;

import org.example.dndmapdisplayerbackend.domain.model.Campaign;

public interface GetCampaignUseCase {
    Campaign getCampaign(Long id, String email);
}
