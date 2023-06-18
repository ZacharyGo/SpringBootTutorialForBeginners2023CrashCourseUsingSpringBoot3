package dev.zach.contentcalendar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.zach.contentcalendar.model.Content;
import dev.zach.contentcalendar.repository.ContentCollectionRepository;

@RestController
@RequestMapping("/api/content")
public class ContentController {

	private final ContentCollectionRepository repository;

	//@Autowired // Implicit don't need this if only 1 public contructor
	public ContentController(ContentCollectionRepository repository) {
		System.out.println("ContentController");
		this.repository = repository;
	}
	
	// Make a request and find all the pieces of content in the system
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }
}
