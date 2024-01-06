package org.example.futurumcampaign.controllers;

import org.example.futurumcampaign.models.Tag;
import org.example.futurumcampaign.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController{
	private final TagService tagService;

	public TagController(TagService tagService){
		this.tagService = tagService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Tag>> getAll(){
		try{
			List<Tag> tags = tagService.getAll();
			return new ResponseEntity<>(tags, HttpStatus.OK);
		} catch(ResponseStatusException e){
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
