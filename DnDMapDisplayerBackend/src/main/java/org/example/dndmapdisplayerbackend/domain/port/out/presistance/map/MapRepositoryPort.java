package org.example.dndmapdisplayerbackend.domain.port.out.presistance.map;


import org.example.dndmapdisplayerbackend.domain.model.Map;

import java.util.List;

public interface MapRepositoryPort {
    Map save(Map map, Long campaignId);
    Map get(Long id);
    Map update(Map map);
    void delete(Long id);
    List<Map> getAllMapsFromUser(Long userId);
    boolean userHasMap(Long id, Long userId);
}
