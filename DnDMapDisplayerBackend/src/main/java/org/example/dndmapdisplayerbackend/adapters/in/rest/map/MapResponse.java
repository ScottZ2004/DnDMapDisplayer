package org.example.dndmapdisplayerbackend.adapters.in.rest.map;

import org.example.dndmapdisplayerbackend.adapters.in.rest.file.ImageResponse;
import org.example.dndmapdisplayerbackend.adapters.in.rest.token.TokenResponse;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.token.TokenEntity;
import org.example.dndmapdisplayerbackend.domain.model.Image;
import org.example.dndmapdisplayerbackend.domain.model.Token;

import java.util.ArrayList;
import java.util.List;

public class MapResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final ImageResponse image;
    private final ImageResponse fogOfWarState;
    private final boolean fogOfWarEnabled;
    private final List<TokenResponse> tokens;
    private final float rowSize;
    private final float columnSize;


    public MapResponse(Long id,
                       String name,
                       String description,
                       float rowSize,
                       float columnSize,
                       Image image,
                       List<Token> tokens,
                       Image fogOfWarState,
                       boolean fogOfWarEnabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.fogOfWarEnabled = fogOfWarEnabled;

        this.image = (image == null) ? null
                : new ImageResponse(image.getId(), image.getUrl(), image.getName());

        this.fogOfWarState = (fogOfWarState == null) ? null
                : new ImageResponse(fogOfWarState.getId(), fogOfWarState.getUrl(), fogOfWarState.getName());

        this.tokens = (tokens == null) ? new ArrayList<>()
                : tokens.stream().map(token -> {
            ImageResponse tokenImage = new ImageResponse(
                    token.getImage().getId(),
                    token.getImage().getUrl(),
                    token.getImage().getName()
            );
            return new TokenResponse(
                    token.getId(),
                    token.getName(),
                    tokenImage,
                    token.getSize(),
                    token.getGridRow(),
                    token.getGridColumn()
            );
        }).toList();

    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public float getRowSize() { return rowSize; }
    public float getColumnSize() { return columnSize; }
    public ImageResponse getImage() {
        return image;
    }
    public List<TokenResponse> getTokens() {
        return tokens;
    }

    public ImageResponse getFogOfWarState() {
        return fogOfWarState;
    }

    public boolean isFogOfWarEnabled() {
        return fogOfWarEnabled;
    }
}