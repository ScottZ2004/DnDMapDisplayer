package org.example.dndmapdisplayerbackend.application.service;

import org.example.dndmapdisplayerbackend.adapters.out.persistence.map.MapRepository;
import org.example.dndmapdisplayerbackend.config.DomainService;
import org.example.dndmapdisplayerbackend.domain.exception.InvalidDataException;
import org.example.dndmapdisplayerbackend.domain.exception.user.UserNotFoundException;
import org.example.dndmapdisplayerbackend.domain.model.Image;
import org.example.dndmapdisplayerbackend.domain.model.Map;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.map.*;
import org.example.dndmapdisplayerbackend.domain.port.out.file.FileStoragePort;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.campaign.CampaignRepositoryPort;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.map.MapRepositoryPort;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.user.UserRepositoryPort;

import java.util.ArrayList;
import java.util.List;

@DomainService
public class MapService implements CreateMapUseCase, UpdateMapUseCase, GetMapUseCase, DeleteMapUseCase, GetAllMapsUseCase {

    private final UserRepositoryPort userRepository;
    private final FileStoragePort fileStorage;
    private final CampaignRepositoryPort campaignRepository;
    private final MapRepositoryPort mapRepositoryPort;

    public MapService(UserRepositoryPort userRepository, FileStoragePort fileStorage, CampaignRepositoryPort campaignRepositoryAdapter, MapRepositoryPort mapRepositoryPort) {
        this.userRepository = userRepository;
        this.fileStorage = fileStorage;
        this.campaignRepository = campaignRepositoryAdapter;
        this.mapRepositoryPort = mapRepositoryPort;
    }

    @Override
    public Map createMap(String name, String description, String filePath, float rowSize, float columnSize, Long campaignId, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);

        validateMap(name, description, filePath, rowSize, columnSize);

        if (!campaignRepository.userHasCampaign(campaignId, user)) {
            throw new InvalidDataException("User does not own this campaign");
        }

        Image image = new Image(
                null,
                filePath,
                fileStorage.getFileName(filePath)
        );

        Map map = new Map(
                null,
                name,
                description,
                image,
                null,
                false,
                new ArrayList<>(),
                rowSize,
                columnSize
        );

        return mapRepositoryPort.save(map, campaignId);



    }

    @Override
    public void deleteMap(Long id, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);

        if (!mapRepositoryPort.userHasMap(id, user.getId())) {
            throw new InvalidDataException("User does not own this map");
        }

        mapRepositoryPort.delete(id);

        // TODo: delete all tokens of the map
    }

    @Override
    public List<Map> getAllMaps(Long userEmail) {

        return null;
    }

    @Override
    public Map getMap(Long id, String userEmail) {

        return null;
    }

    @Override
    public Map updateMap(Long id, String name, String description, String filePath, String fogOfWareFilePath, float rowSize, float columnSize, String userEmail) {

        return null;
    }

    private void validateMap(String name, String description, String filePath, float rowSize, float columnSize) {
        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Name cannot be null or blank");
        }

        if (description == null || description.isBlank()) {
            throw new InvalidDataException("Description cannot be null or blank");
        }

        if (filePath == null || filePath.isBlank()) {
            throw new InvalidDataException("A file must be provided");
        }

        if (rowSize <= 0) {
            throw new InvalidDataException("Row size must be greater than 0");
        }

        if (columnSize <= 0) {
            throw new InvalidDataException("Column size must be greater than 0");
        }
    }
}
