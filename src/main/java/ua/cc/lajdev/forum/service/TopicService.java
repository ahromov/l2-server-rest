package ua.cc.lajdev.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.forum.model.Topic;
import ua.cc.lajdev.forum.repo.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository repository;

	public Page<Topic> getNextPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
