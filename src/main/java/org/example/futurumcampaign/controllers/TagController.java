package org.example.futurumcampaign.controllers;

import org.example.futurumcampaign.models.Tag;
import org.example.futurumcampaign.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tag")
@Transactional
public class TagController {
	private final TagService tagService;

	public TagController(TagService tagService) {
		this.tagService = tagService;
	}

	@GetMapping
	public ResponseEntity<List<Tag>> getAll() {
		try {
			List<Tag> tags = tagService.getAll();
			return new ResponseEntity<>(tags, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
		try {
			Tag newTag = tagService.addTag(tag);
			return new ResponseEntity<>(newTag, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getStatusCode());
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
