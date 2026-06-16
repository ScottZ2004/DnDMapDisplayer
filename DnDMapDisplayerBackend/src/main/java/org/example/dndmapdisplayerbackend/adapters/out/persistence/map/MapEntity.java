package org.example.dndmapdisplayerbackend.adapters.out.persistence.map;

import jakarta.persistence.*;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.image.ImageEntity;
import org.jspecify.annotations.Nullable;

@Entity
@Table(name = "maps")
public class MapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "campaign_id")
    private Long campaignId;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @Nullable
    @OneToOne
    @JoinColumn(name = "fog_of_war_state_id")
    private ImageEntity fogOfWarState;

    @Column(name = "fog_of_war_enabled")
    private boolean fogOfWarEnabled;

    @Column(name = "row_size")
    private float rowSize;

    @Column(name = "column_size")
    private float columnSize;

    public MapEntity() {
    }

    public MapEntity(Long id, String name, String description, Long campaignId, ImageEntity image, @Nullable ImageEntity fogOfWarState, boolean fogOfWarEnabled, float rowSize, float columnSize) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.campaignId = campaignId;
        this.image = image;
        this.fogOfWarState = fogOfWarState;
        this.fogOfWarEnabled = fogOfWarEnabled;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public @Nullable ImageEntity getFogOfWarState() {
        return fogOfWarState;
    }

    public void setFogOfWarState(@Nullable ImageEntity fogOfWarState) {
        this.fogOfWarState = fogOfWarState;
    }

    public boolean isFogOfWarEnabled() {
        return fogOfWarEnabled;
    }

    public void setFogOfWarEnabled(boolean fogOfWarEnabled) {
        this.fogOfWarEnabled = fogOfWarEnabled;
    }

    public float getRowSize() {
        return rowSize;
    }

    public void setRowSize(float rowSize) {
        this.rowSize = rowSize;
    }

    public float getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(float columnSize) {
        this.columnSize = columnSize;
    }
}
