package org.example.futurumcampaign.repositories;

import org.example.futurumcampaign.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
	boolean existsByNameAndLastNameAndCompanyName(String name, String lastName, String companyName);

	Seller findSellerByNameAndLastNameAndCompanyName(String name, String lastName, String companyName);
}
