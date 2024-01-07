package org.example.futurumcampaign.repositories;

import org.example.futurumcampaign.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
	boolean existsCampaignByName(String name);
	Campaign getCampaignByName(String name);
}
