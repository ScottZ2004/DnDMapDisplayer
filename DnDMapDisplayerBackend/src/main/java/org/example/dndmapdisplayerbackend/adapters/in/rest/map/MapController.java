package org.example.dndmapdisplayerbackend.adapters.in.rest.map;

import org.example.dndmapdisplayerbackend.domain.model.Map;
import org.example.dndmapdisplayerbackend.domain.port.in.map.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.validation.Validator;

@RestController
@RequestMapping("api/maps")
public class MapController {

    private final CreateMapUseCase createMapUseCase;
    private final GetMapUseCase getMapUseCase;
    private final DeleteMapUseCase deleteMapUseCase;
    private final GetAllMapsUseCase getAllMapsUseCase;
    private final UpdateMapUseCase updateMapUseCase;

    public MapController(CreateMapUseCase createMapUseCase, GetMapUseCase getMapUseCase, DeleteMapUseCase deleteMapUseCase, GetAllMapsUseCase getAllMapsUseCase, UpdateMapUseCase updateMapUseCase) {
        this.createMapUseCase = createMapUseCase;
        this.getMapUseCase = getMapUseCase;
        this.deleteMapUseCase = deleteMapUseCase;
        this.getAllMapsUseCase = getAllMapsUseCase;
        this.updateMapUseCase = updateMapUseCase;
    }

    @PostMapping
    public MapResponse createMap(
            @RequestBody CreateMapRequest request,
            @AuthenticationPrincipal String email) {
        Map map = createMapUseCase.createMap(
                request.name(),
                request.description(),
                request.filePath(),
                request.rowSize(),
                request.columnSize(),
                request.campaignId(),
                email
                );
        return new MapResponse(
                map.getId(),
                map.getName(),
                map.getDescription(),
                map.getRowSize(),
                map.getColumnSize(),
                map.getImage(),
                map.getTokens(),
                map.getFogOfWarState(),
                map.isFogOfWarEnabled()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMap(@PathVariable Long id, @AuthenticationPrincipal String email) {
        deleteMapUseCase.deleteMap(id, email);
        return ResponseEntity.ok("Map deleted successfully");
    }
}
