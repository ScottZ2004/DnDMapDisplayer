package org.example.dndmapdisplayerbackend.domain.port.in.map;

import org.example.dndmapdisplayerbackend.domain.model.Map;

public interface GetMapUseCase {
    Map getMap(Long id, String userEmail);
}
