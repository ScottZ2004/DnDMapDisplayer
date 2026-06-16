package org.example.dndmapdisplayerbackend.domain.port.in.map;

import org.example.dndmapdisplayerbackend.domain.model.Map;

public interface CreateMapUseCase {
    Map createMap(String name, String description, String filePath, float rowSize, float columnSize, Long campaignId, String userEmail);
}
