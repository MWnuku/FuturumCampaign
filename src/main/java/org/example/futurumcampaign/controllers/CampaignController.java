package org.example.futurumcampaign.controllers;

import org.example.futurumcampaign.models.Campaign;
import org.example.futurumcampaign.services.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/campaign")
public class CampaignController {
	private final CampaignService campaignService;

	public CampaignController(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	@GetMapping
	public ResponseEntity<List<Campaign>> getAllCampaign() {
		try {
			List<Campaign> campaigns = campaignService.getAll();
			return new ResponseEntity<>(campaigns, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Campaign> getCampaignById(@PathVariable("id") Long id) {
		try {
			Campaign campaign = campaignService.getCampaignById(id);
			return new ResponseEntity<>(campaign, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Campaign> addCampaign(@RequestBody Campaign campaign) {
		try {
			Campaign newCampaign = campaignService.addCampaign(campaign);
			return new ResponseEntity<>(newCampaign, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Campaign> updateCampaignById(@PathVariable("id") Long id, @RequestBody Campaign campaign) {
		try {
			Campaign existingCampaign = campaignService.updateCampaignById(id, campaign);
			return new ResponseEntity<>(existingCampaign, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCampaignById(@PathVariable("id") Long id) {
		try {
			campaignService.deleteCampaignById(id);
			return new ResponseEntity<>("Campaign deleted successfully.", HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
