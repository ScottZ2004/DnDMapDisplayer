package org.example.dndmapdisplayerbackend.adapters.out.persistence.map;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<MapEntity, Long> {
}
