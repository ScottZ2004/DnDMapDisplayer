package org.example.dndmapdisplayerbackend.adapters.out.persistence.campaign;

import org.example.dndmapdisplayerbackend.adapters.out.persistence.user.UserEntity;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.user.UserRepository;
import org.example.dndmapdisplayerbackend.domain.exception.Campaign.CampaignNotFoundException;
import org.example.dndmapdisplayerbackend.domain.exception.NotFoundException;
import org.example.dndmapdisplayerbackend.domain.exception.user.UserNotFoundException;
import org.example.dndmapdisplayerbackend.domain.model.Campaign;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.campaign.CampaignRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampaignRepositoryAdapter implements CampaignRepositoryPort {
    private final CampaignRepository repository;
    private final UserRepository userRepository;

    public CampaignRepositoryAdapter(CampaignRepository repository, UserRepository userRepository) {
        this.repository = repository;

        this.userRepository = userRepository;
    }


    @Override
    public Campaign getCampaign(Long id) {
        CampaignEntity campaignEntity = repository.findById(id).orElse(null);
        if (campaignEntity == null) {
            throw new NotFoundException("Campaign not found");
        }
        return new Campaign(
                campaignEntity.getId(),
                campaignEntity.getName(),
                campaignEntity.getDescription()
        );
    }

    @Override
    public List<Campaign> getAllCampaignsFromUser(User user) {
        UserEntity userEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);
        List<CampaignEntity> campaignEntities = repository.getCampaignEntitiesByUser(userEntity);
        return campaignEntities.stream()
                .map(campaignEntity -> new Campaign(
                        campaignEntity.getId(),
                        campaignEntity.getName(),
                        campaignEntity.getDescription()
                )).toList();
    }

    @Override
    public Campaign save(Campaign campaign, User user) {
        UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole(), user.isEmailVerified());

        CampaignEntity campaignEntity = new CampaignEntity(
                campaign.getId(),
                campaign.getName(),
                campaign.getDescription(),
                userEntity
        );
        CampaignEntity saved = repository.save(campaignEntity);

        return new Campaign(
                saved.getId(),
                saved.getName(),
                saved.getDescription()
        );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Campaign update(Campaign campaign) {
        CampaignEntity campaignEntity = new CampaignEntity(
                campaign.getId(),
                campaign.getName(),
                campaign.getDescription()
        );
        CampaignEntity saved = repository.save(campaignEntity);
        return new Campaign(
                saved.getId(),
                saved.getName(),
                saved.getDescription()
        );
    }

    @Override
    public boolean userHasCampaign(Long id, User user) {
        UserEntity userEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);
        CampaignEntity campaignEntity = repository.findById(id)
                .orElseThrow(CampaignNotFoundException::new);
        return campaignEntity.getUser().equals(userEntity);
    }
}
