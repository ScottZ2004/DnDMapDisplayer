package org.example.dndmapdisplayerbackend.application.service;

import org.example.dndmapdisplayerbackend.adapters.out.persistence.user.UserRepositoryAdapter;
import org.example.dndmapdisplayerbackend.config.DomainService;
import org.example.dndmapdisplayerbackend.domain.exception.InvalidDataException;
import org.example.dndmapdisplayerbackend.domain.exception.NotFoundException;
import org.example.dndmapdisplayerbackend.domain.exception.user.UserNotFoundException;
import org.example.dndmapdisplayerbackend.domain.model.Campaign;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.campaign.*;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.campaign.CampaignRepositoryPort;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.user.UserRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainService
public class CampaignService implements CreateCampaignUseCase, GetCampaignUseCase, DeleteCampaignUseCase, UpdateCampaignUseCase, GetAllCampaignsFromUserUseCase {

    UserRepositoryPort userRepository;
    CampaignRepositoryPort repository;

    public CampaignService(CampaignRepositoryPort repository, UserRepositoryPort userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Campaign getCampaign(Long id, String email) {
        return repository.getCampaign(id);
    }

    @Override
    public Campaign createCampaign(String name, String description, String email) {
        validateCampaign(name, description);
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return repository.save(new Campaign(null, name, description), user);
    }

    @Override
    @Transactional
    public void deleteCampaign(Long id, String email) {
        if (!repository.userHasCampaign(id, userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new))) {
            throw new InvalidDataException("user does not have this campaign");
        }
        repository.delete(id);
        // Todo: Delete all tokens of the maps in the campaign
    }

    @Override
    public Campaign updateCampaign(Long id, String name, String description, String email) {
        validateCampaign(name, description);
        if (repository.userHasCampaign(id, userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new))) {
            return repository.update(new Campaign(id, name, description));
        }
        throw new NotFoundException("Campaign not found");
    }

    @Override
    public List<Campaign> getAllCampaignsFromUser(String email) {
        return repository.getAllCampaignsFromUser(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    private void validateCampaign(String name, String description) {
        if (name.isBlank()) {
            throw new InvalidDataException("Name cannot be blank");
        }

        if (description.isBlank()) {
            throw new InvalidDataException("Description cannot be blank");
        }
    }

}
