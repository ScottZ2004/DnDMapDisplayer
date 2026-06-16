package org.example.dndmapdisplayerbackend.domain.model;

public class Image {
    private final Long id;
    private final String url;
    private final String name;


    public Image(Long id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
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
}
