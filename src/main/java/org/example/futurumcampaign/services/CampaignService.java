package org.example.futurumcampaign.services;

import org.example.futurumcampaign.models.Campaign;
import org.example.futurumcampaign.models.Seller;
import org.example.futurumcampaign.models.Tag;
import org.example.futurumcampaign.repositories.CampaignRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CampaignService{
	private final CampaignRepository campaignRepository;
	private final SellerService sellerService;
	private final TagService tagService;

	public CampaignService(CampaignRepository campaignRepository, SellerService sellerService, TagService tagService){
		this.campaignRepository = campaignRepository;
		this.sellerService = sellerService;
		this.tagService = tagService;
	}

	public Campaign addCampaign(Campaign campaign){
		if(campaignRepository.existsCampaignByName(campaign.getName())){
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "There is already a campaign with this name.");
		}

		if(!sellerService.existsByNameAndLastNameAndCompanyName(campaign.getSeller().getName(), campaign.getSeller().getLastName(), campaign.getSeller().getCompanyName())){
			Seller seller = campaign.getSeller();
			Seller addedSeller =  sellerService.addSeller(seller);
			campaign.setSeller(addedSeller);
		}
		else{
			Seller seller = sellerService.findByNameAndLastNameAndCompanyName(campaign.getSeller().getName(), campaign.getSeller().getLastName(), campaign.getSeller().getCompanyName());
			campaign.setSeller(seller);
		}

		return campaignRepository.save(campaign);
	}

	public Campaign updateCampaignById(Long id, Campaign newCampaign){
		try{
			Optional<Campaign> optionalCampaign = campaignRepository.findById(id);

			if(optionalCampaign.isPresent()){
				Campaign existingCampaign = optionalCampaign.get();

				existingCampaign.setName(newCampaign.getName());
				existingCampaign.setStatus(newCampaign.getStatus());
				existingCampaign.setRadius(newCampaign.getRadius());
				existingCampaign.setTown(newCampaign.getTown());
				existingCampaign.setBidAmount(newCampaign.getBidAmount());

				List<Tag> tags = new ArrayList<>();
				for(Tag tag : newCampaign.getTags()){
					tags.add(tagService.findByKeyword(tag.getKeyword()));
				}
				existingCampaign.setTags(tags);

				if(sellerService.existsByNameAndLastNameAndCompanyName(newCampaign.getSeller().getName(), newCampaign.getSeller().getLastName(), newCampaign.getSeller().getCompanyName())){
					Seller seller = sellerService.findByNameAndLastNameAndCompanyName(newCampaign.getSeller().getName(), newCampaign.getSeller().getLastName(), newCampaign.getSeller().getCompanyName());
					existingCampaign.setSeller(seller);
				} else{
					Seller seller = sellerService.addSeller(newCampaign.getSeller());
					existingCampaign.setSeller(seller);
				}

				return campaignRepository.save(existingCampaign);
			} else{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
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
