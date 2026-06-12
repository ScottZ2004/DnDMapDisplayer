package org.example.dndmapdisplayerbackend.domain.exception.Campaign;

public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException() {
        super("Campaign not found.");
    }
}
