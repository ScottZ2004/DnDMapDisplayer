package org.example.dndmapdisplayerbackend.domain.exception.campaign;

public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException() {
        super("Campaign not found.");
    }
}
