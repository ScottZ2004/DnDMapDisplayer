package org.example.dndmapdisplayerbackend.domain.port.in.map;

import org.example.dndmapdisplayerbackend.domain.model.Map;

public interface UpdateMapUseCase {
    Map updateMap(Long id, String name, String description, String filePath, String fogOfWareFilePath, float rowSize, float columnSize, String userEmail);
}
