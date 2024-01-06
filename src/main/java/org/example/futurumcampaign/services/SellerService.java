package org.example.futurumcampaign.services;

import org.example.futurumcampaign.models.Seller;
import org.example.futurumcampaign.repositories.SellerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService{
	private final SellerRepository sellerRepository;

	public SellerService(SellerRepository sellerRepository){
		this.sellerRepository = sellerRepository;
	}

	public Seller addSeller(Seller seller){
		return sellerRepository.save(seller);
	}

	public List<Seller> getAll(){
		List<Seller> sellers = new ArrayList<>();
		sellers.addAll(sellerRepository.findAll());

		if(!sellers.isEmpty()){
			return sellers;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	public Seller getSellerById(Long id){
		Optional<Seller> seller = sellerRepository.findById(id);

		if(seller.isPresent()){
			return seller.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public Seller updateSellerById(Long id, Seller newSeller){
		Optional<Seller> optionalSeller = sellerRepository.findById(id);

		if(optionalSeller.isPresent()){
			Seller oldSeller = optionalSeller.get();

			oldSeller.setBalance(newSeller.getBalance());
			oldSeller.setCampaigns(newSeller.getCampaigns());
			oldSeller.setName(newSeller.getName());
			oldSeller.setLastName(newSeller.getLastName());
			oldSeller.setCompanyName(newSeller.getCompanyName());

			return sellerRepository.save(oldSeller);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public void deleteSellerById(Long id){
		if(sellerRepository.existsById(id)){
			sellerRepository.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no seller with this id.");
		}
	}

	public boolean existsById(Long id){
		return sellerRepository.existsById(id);
	}
	public boolean existsByNameAndLastNameAndCompanyName(String name, String lastName, String companyName){
		return sellerRepository.existsByNameAndLastNameAndCompanyName(name, lastName, companyName);
	}
}