package org.example.futurumcampaign.controllers;

import org.example.futurumcampaign.models.Seller;
import org.example.futurumcampaign.services.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
	private final SellerService sellerService;

	public SellerController(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	@GetMapping()
	public ResponseEntity<List<Seller>> getAllSellers() {
		try {
			List<Seller> sellers = sellerService.getAll();
			return new ResponseEntity<>(sellers, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
		try {
			Seller seller = sellerService.getSellerById(id);
			return new ResponseEntity<>(seller, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
		try {
			Seller newSeller = sellerService.addSeller(seller);
			return new ResponseEntity<>(newSeller, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{id}")
	public ResponseEntity<Seller> updateSeller(@PathVariable("id") Long id, @RequestBody Seller seller) {
		try {
			Seller sellerUpd = sellerService.updateSellerById(id, seller);
			return new ResponseEntity<>(sellerUpd, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSellerById(@PathVariable("id") Long id) {
		try {
			sellerService.deleteSellerById(id);
			return new ResponseEntity<>("Seller deleted successfully.", HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
