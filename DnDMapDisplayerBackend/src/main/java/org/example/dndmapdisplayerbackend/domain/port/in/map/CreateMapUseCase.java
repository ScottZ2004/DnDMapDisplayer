package org.example.dndmapdisplayerbackend.domain.port.in.map;

public interface CreateMapUseCase {
    void createMap(String name, String description, Long campaignId);
}
