package org.example.dndmapdisplayerbackend.domain.port.in.campaign;

public interface DeleteCampaignUseCase {
    void deleteCampaign(Long id, String email);
}
