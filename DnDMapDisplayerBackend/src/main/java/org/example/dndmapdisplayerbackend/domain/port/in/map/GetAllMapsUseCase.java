package org.example.dndmapdisplayerbackend.domain.port.in.map;

import org.example.dndmapdisplayerbackend.domain.model.Map;

import java.util.List;

public interface GetAllMapsUseCase {
    List<Map> getAllMaps(Long userEmail);
}
