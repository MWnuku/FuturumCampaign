package org.example.futurumcampaign;

import org.example.futurumcampaign.controllers.CampaignController;
import org.example.futurumcampaign.controllers.SellerController;
import org.example.futurumcampaign.controllers.TagController;
import org.example.futurumcampaign.models.*;
import org.example.futurumcampaign.services.TagService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {
	private final CampaignController campaignController;
	private final SellerController sellerController;
	private final TagController tagController;
	private final TagService tagService;

	public DataLoader(CampaignController campaignController, SellerController sellerController, TagController tagController, TagService tagService) {
		this.campaignController = campaignController;
		this.sellerController = sellerController;
		this.tagController = tagController;
		this.tagService = tagService;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Seller seller = new Seller("Company", "Imie", "Nazwisko", 100000000000., new ArrayList<>());
		Seller seller2 = new Seller("Futurum", "Pracownik", "Marketingu", 2202000000000.50, new ArrayList<>());

		sellerController.addSeller(seller);
		sellerController.addSeller(seller2);

		for(KeywordEnum keywordEnum : KeywordEnum.values()) {
			tagController.addTag(new Tag(keywordEnum));
		}

		Tag[] tags = {tagService.findByKeyword(KeywordEnum.PCs), tagService.findByKeyword(KeywordEnum.Electronics)};
		Tag[] tags2 = {tagService.findByKeyword(KeywordEnum.SmartWatches)};

		Campaign campaign = new Campaign("Nazwa", new HashSet<>(Arrays.asList(tags)), 100000., Status.On, Town.Katowice, 10000., seller);
		Campaign campaign2 = new Campaign("FuturumCampaign", new HashSet<>(Arrays.asList(tags2)), 155000., Status.Off, Town.Krakow, 100., seller2);

		campaignController.addCampaign(campaign);
		campaignController.addCampaign(campaign2);
	}
}
