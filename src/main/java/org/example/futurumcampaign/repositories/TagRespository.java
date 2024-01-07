package org.example.futurumcampaign.repositories;

import org.example.futurumcampaign.models.KeywordEnum;
import org.example.futurumcampaign.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRespository extends JpaRepository<Tag, Long>{
	boolean existsByKeyword(KeywordEnum keywordEnum);
	Tag findByKeyword(KeywordEnum keywordEnum);
}
