package org.example.dndmapdisplayerbackend.domain.model;

import java.util.List;

public class Map {
    private final Long id;
    private final String name;
    private final String description;
    private final Image image;
    private final Image fogOfWarState;
    private final boolean fogOfWarEnabled;
    private final List<Token> tokens;
    private final float rowSize;
    private final float columnSize;

    public Map(Long id, String name, String description, Image image, Image fogOfWarState, boolean fogOfWarEnabled, List<Token> tokens, float rowSize, float columnSize) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.fogOfWarState = fogOfWarState;
        this.fogOfWarEnabled = fogOfWarEnabled;
        this.tokens = tokens;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public Image getFogOfWarState() {
        return fogOfWarState;
    }

    public boolean isFogOfWarEnabled() {
        return fogOfWarEnabled;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public float getRowSize() {
        return rowSize;
    }

    public float getColumnSize() {
        return columnSize;
    }
}
