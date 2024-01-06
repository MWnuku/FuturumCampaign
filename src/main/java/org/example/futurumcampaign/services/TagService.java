package org.example.futurumcampaign.services;

import org.example.futurumcampaign.models.Tag;
import org.example.futurumcampaign.repositories.TagRespository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService{
	private final TagRespository tagRespository;

	public TagService(TagRespository tagRespository){
		this.tagRespository = tagRespository;
	}

	public List<Tag> getAll(){
		List<Tag> tags = new ArrayList<>(tagRespository.findAll());

		if(tags.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		else {
			return tags;
		}
	}
}
