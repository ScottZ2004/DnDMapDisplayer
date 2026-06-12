package org.example.dndmapdisplayerbackend.domain.port.in.campaign;

import org.example.dndmapdisplayerbackend.domain.model.Campaign;

import java.util.List;

public interface GetAllCampaignsFromUserUseCase {
    List<Campaign> getAllCampaignsFromUser(String email);
}
