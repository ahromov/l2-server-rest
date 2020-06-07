package ua.cc.lajdev.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.forum.model.Topic;
import ua.cc.lajdev.forum.repo.TopicRepository;
import ua.cc.lajdev.forum.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository repository;

	@Override
	public Page<Topic> getPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
