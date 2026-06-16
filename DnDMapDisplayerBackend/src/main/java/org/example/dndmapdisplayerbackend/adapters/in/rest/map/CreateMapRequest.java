package org.example.dndmapdisplayerbackend.adapters.in.rest.map;

public record CreateMapRequest(
        String name,
        String description,
        String filePath,
        float rowSize,
        float columnSize,
        Long campaignId
        ){
}
