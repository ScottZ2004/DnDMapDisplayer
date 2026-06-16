package org.example.dndmapdisplayerbackend.adapters.out.persistence.map;

import org.example.dndmapdisplayerbackend.adapters.out.persistence.image.ImageEntity;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.image.ImageRepository;
import org.example.dndmapdisplayerbackend.domain.model.Image;
import org.example.dndmapdisplayerbackend.domain.model.Map;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.map.MapRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapRepositoryAdapter implements MapRepositoryPort {

    MapRepository repository;

    ImageRepository imageRepository;

    MapRepositoryAdapter(MapRepository repository, ImageRepository imageRepository) {
        this.repository = repository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Map save(Map map, Long campaignId) {

        ImageEntity imageEntity = new ImageEntity(
                null,
                map.getImage().getUrl(),
                map.getImage().getName()
        );

        ImageEntity savedimageEntity = imageRepository.save(imageEntity);

        MapEntity mapEntity = getMapEntity(map, campaignId, savedimageEntity);

        MapEntity saved = repository.save(mapEntity);

        Image savedImage = new Image(
                saved.getImage().getId(),
                saved.getImage().getUrl(),
                saved.getImage().getName()
        );

        return new Map(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                savedImage,
                null,
                saved.isFogOfWarEnabled(),
                null,
                saved.getRowSize(),
                saved.getColumnSize()
        );
    }



    @Override
    public Map get(Long id) {
        return null;
    }

    @Override
    public Map update(Map map) {
        return null;
    }

    @Override
    public Map delete(Long id) {
        return null;
    }

    @Override
    public List<Map> getAllMapsFromUser(Long userId) {
        return List.of();
    }

    @Override
    public boolean userHasMap(Long id, Long userId) {
        return false;
    }

    private static MapEntity getMapEntity(Map map, Long campaignId, ImageEntity imageEntity) {


        return new MapEntity(
                map.getId(),
                map.getName(),
                map.getDescription(),
                campaignId,
                imageEntity,
                null,
                map.isFogOfWarEnabled(),
                map.getRowSize(),
                map.getColumnSize()
        );
    }
}
