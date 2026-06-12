package org.example.dndmapdisplayerbackend.domain.model;

public class Image {
    private final Long id;
    private final String url;
    private final String name;
    private final float width;
    private final float height;


    public Image(Long id, String url, String name, float width, float height) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public Long getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public String getName() {
        return name;
    }
    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
