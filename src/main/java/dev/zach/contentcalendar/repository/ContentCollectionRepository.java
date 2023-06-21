package dev.zach.contentcalendar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.zach.contentcalendar.model.Content;
import dev.zach.contentcalendar.model.Status;
import dev.zach.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository {

	private final List<Content> contentList = new ArrayList<>();

	public ContentCollectionRepository() {

	}

	public List<Content> findAll() {
		return contentList;
	}
	
	public Optional<Content> findById(Integer id) {
		return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
	}
	
	public void save(Content content) {
		//contentList.removeIf(c->c.id().equals(content.id()));
		delete(content.id());
		contentList.add(content);
	}

	@PostConstruct
	private void init() {
		Content content = new Content( 1,
		"My First Blog Post",
		"My first blog post",
		Status.IDEA,
		Type.ARTICLE,
		LocalDateTime.now(),
		null,
		"");
		contentList.add(content);
	}

	public boolean existByID(Integer id) {
		return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
	}

	public void delete(Integer id) {
		contentList.removeIf(c->c.id().equals(id));
	}
	
}
