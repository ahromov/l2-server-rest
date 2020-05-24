package ua.cc.lajdev.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.forum.model.Post;
import ua.cc.lajdev.forum.repo.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Page<Post> getNextPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
