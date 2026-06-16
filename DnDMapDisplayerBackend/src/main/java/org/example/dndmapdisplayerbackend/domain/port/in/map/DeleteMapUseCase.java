package org.example.dndmapdisplayerbackend.domain.port.in.map;

public interface DeleteMapUseCase {
    void deleteMap(Long id, String userEmail);
}
