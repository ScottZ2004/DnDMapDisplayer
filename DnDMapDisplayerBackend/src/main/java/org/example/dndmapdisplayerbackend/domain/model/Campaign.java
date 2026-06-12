package org.example.dndmapdisplayerbackend.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Campaign {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Map> maps;

    public Campaign(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maps = new ArrayList<>();
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

    public List<Map> getMaps() {
        return maps;
    }
}
