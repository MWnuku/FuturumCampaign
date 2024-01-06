package org.example.futurumcampaign.services;

import org.example.futurumcampaign.models.Campaign;
import org.example.futurumcampaign.models.Seller;
import org.example.futurumcampaign.repositories.CampaignRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignService{
	private final CampaignRepository campaignRepository;
	private final SellerService sellerService;

	public CampaignService(CampaignRepository campaignRepository, SellerService sellerService){
		this.campaignRepository = campaignRepository;
		this.sellerService = sellerService;
	}

	public Campaign addCampaign(Campaign campaign){
		if(!sellerService.existsByNameAndLastNameAndCompanyName(campaign.getSeller().getName(), campaign.getSeller().getLastName(), campaign.getSeller().getCompanyName())){
			Seller seller = sellerService.addSeller(campaign.getSeller());
			campaign.setSeller(seller);
		}

		return campaignRepository.save(campaign);
	}

	public Campaign updateCampaignById(Long id, Campaign newCampaign){
		Optional<Campaign> optionalCampaign = campaignRepository.findById(id);

		if(optionalCampaign.isPresent()){
			Campaign campaign = optionalCampaign.get();

			campaign.setTags(newCampaign.getTags());
			campaign.setName(newCampaign.getName());
			campaign.setStatus(newCampaign.getStatus());
			campaign.setRadius(newCampaign.getRadius());
			campaign.setTown(newCampaign.getTown());
			campaign.setBidAmount(newCampaign.getBidAmount());
			campaign.setSeller(newCampaign.getSeller());

			return campaignRepository.save(campaign);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public Campaign getCampaignById(Long id){
		Optional<Campaign> campaign = campaignRepository.findById(id);

		if(campaign.isPresent()){
			return campaign.get();
		}
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public List<Campaign> getAll(){
		List<Campaign> campaigns = new ArrayList<>();
		campaigns.addAll(campaignRepository.findAll());

		if(!campaigns.isEmpty())
			return campaigns;
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	public void deleteCampaignById(Long id){
		if(campaignRepository.existsById(id))
			campaignRepository.deleteById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no seller with this id.");
		}
	}
}
