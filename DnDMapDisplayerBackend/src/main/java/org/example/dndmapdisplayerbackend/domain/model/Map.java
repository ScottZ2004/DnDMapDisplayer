package org.example.dndmapdisplayerbackend.domain.model;

import java.util.List;

public class Map {
    private final Long id;
    private final String name;
    private final String description;
    private final Image image;
    private final Image fogOfWarState;
    private final List<Token> tokens;
    private final float RowSize;
    private final float ColumnSize;

    public Map(Long id, String name, String description, Image image, Image fogOfWarState, List<Token> tokens, float RowSize, float ColumnSize, float columnSize) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.fogOfWarState = fogOfWarState;
        this.tokens = tokens;
        this.RowSize = RowSize;
        this.ColumnSize = columnSize;
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

    public List<Token> getTokens() {
        return tokens;
    }

    public float getRowSize() {
        return RowSize;
    }

    public float getColumnSize() {
        return ColumnSize;
    }
}
