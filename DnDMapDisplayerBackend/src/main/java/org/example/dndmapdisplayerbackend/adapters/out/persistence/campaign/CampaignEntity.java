package org.example.dndmapdisplayerbackend.adapters.out.persistence.campaign;

import jakarta.persistence.*;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.AuditableEntity;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.user.UserEntity;

@Entity
@Table(name = "campaigns")
public class CampaignEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CampaignEntity() {}

    public CampaignEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CampaignEntity(Long id, String name, String description, UserEntity user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
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
}
