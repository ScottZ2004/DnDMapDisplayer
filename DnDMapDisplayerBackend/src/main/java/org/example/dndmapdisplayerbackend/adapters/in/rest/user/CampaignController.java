package org.example.dndmapdisplayerbackend.adapters.in.rest.user;

import org.example.dndmapdisplayerbackend.adapters.in.rest.user.request.CreateCampaignRequest;
import org.example.dndmapdisplayerbackend.adapters.in.rest.user.response.CampaignResponse;
import org.example.dndmapdisplayerbackend.domain.model.Campaign;
import org.example.dndmapdisplayerbackend.domain.port.in.campaign.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/campaigns")
public class CampaignController {
    private final CreateCampaignUseCase createCampaignUseCase;
    private final GetCampaignUseCase getCampaignUseCase;
    private final DeleteCampaignUseCase deleteCampaignUseCase;
    private final UpdateCampaignUseCase updateCampaignUseCase;
    private final GetAllCampaignsFromUserUseCase getAllCampaignsFromUserUseCase;

    public CampaignController(CreateCampaignUseCase createCampaignUseCase,
                              GetCampaignUseCase getCampaignUseCase,
                              DeleteCampaignUseCase deleteCampaignUseCase,
                              UpdateCampaignUseCase updateCampaignUseCase,
                              GetAllCampaignsFromUserUseCase getAllCampaignsFromUserUseCase) {
        this.createCampaignUseCase = createCampaignUseCase;
        this.getCampaignUseCase = getCampaignUseCase;
        this.deleteCampaignUseCase = deleteCampaignUseCase;
        this.updateCampaignUseCase = updateCampaignUseCase;
        this.getAllCampaignsFromUserUseCase = getAllCampaignsFromUserUseCase;
    }

    @PostMapping()
    public CampaignResponse createCampaign(@RequestBody CreateCampaignRequest request,
                                           @AuthenticationPrincipal String email) {
        Campaign campaign = createCampaignUseCase.createCampaign(request.name(), request.description(), email);
        return new CampaignResponse(campaign.getId(), campaign.getName(), campaign.getDescription());
    }

    @GetMapping("/{id}")
    public CampaignResponse getCampaign(@PathVariable Long id, @AuthenticationPrincipal String email) {
        Campaign campaign = getCampaignUseCase.getCampaign(id, email);
        return new CampaignResponse(campaign.getId(), campaign.getName(), campaign.getDescription());
    }

    @GetMapping()
    public List<CampaignResponse> getAllCampaigns(@AuthenticationPrincipal String email) {
        return getAllCampaignsFromUserUseCase.getAllCampaignsFromUser(email).stream()
                .map(campaign -> new CampaignResponse(campaign.getId(), campaign.getName(), campaign.getDescription()))
                .toList();
    }

    @PutMapping("/{id}")
    public CampaignResponse updateCampaign(@PathVariable Long id, @RequestBody CreateCampaignRequest request, @AuthenticationPrincipal String email) {
        Campaign campaign = updateCampaignUseCase.updateCampaign(id, request.name(), request.description(), email);
        return new CampaignResponse(campaign.getId(), campaign.getName(), campaign.getDescription());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long id, @AuthenticationPrincipal String email) {
        deleteCampaignUseCase.deleteCampaign(id, email);
        return ResponseEntity.ok("Campaign deleted successfully");
    }
}
