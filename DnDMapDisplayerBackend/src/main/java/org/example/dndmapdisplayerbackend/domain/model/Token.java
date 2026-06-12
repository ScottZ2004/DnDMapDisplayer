package org.example.dndmapdisplayerbackend.domain.model;

public class Token {
    private final Long id;
    private final String name;
    private final Image image;
    private final Size size;
    private final float gridRow;
    private final float gridColumn;

    public Token(Long id, String name, Image image, Size size, float gridRow, float gridColumn) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.size = size;
        this.gridRow = gridRow;
        this.gridColumn = gridColumn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public Size getSize() {
        return size;
    }

    public float getGridRow() {
        return gridRow;
    }

    public float getGridColumn() {
        return gridColumn;
    }
}
