package dev.zach.contentcalendar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.zach.contentcalendar.model.Content;
import dev.zach.contentcalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
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
    
    @GetMapping("/{id}")
    public Optional<Content> findById(@PathVariable Integer id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.")));
    }
    
   // @Valid Marks a property, method parameter or method return type for validation cascading.
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) { 
    	repository.save(content);
    }
    
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, 
) {
    	if(!repository.existByID(id)) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
    	}
    	repository.save(content);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
    	repository.delete(id);
    }
}
