package org.example.futurumcampaign.services;

import org.example.futurumcampaign.models.KeywordEnum;
import org.example.futurumcampaign.models.Tag;
import org.example.futurumcampaign.repositories.TagRespository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagService {
	private final TagRespository tagRespository;

	public TagService(TagRespository tagRespository) {
		this.tagRespository = tagRespository;
	}

	public List<Tag> getAll() throws ResponseStatusException {
		List<Tag> tags = new ArrayList<>(tagRespository.findAll());

		if(tags.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} else {
			return tags;
		}
	}

	public Tag addTag(Tag tag) {
		if(!tagRespository.existsByKeyword(tag.getKeyword())) {
			return tagRespository.save(tag);
		} else {
			return tagRespository.findByKeyword(tag.getKeyword());
		}
	}

	public Tag findByKeyword(KeywordEnum keywordEnum) {
		return tagRespository.findByKeyword(keywordEnum);
	}
}
