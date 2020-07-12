package ua.cc.lajdev.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.forum.model.Topic;
import ua.cc.lajdev.forum.repo.TopicRepository;
import ua.cc.lajdev.forum.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	private final TopicRepository repository;

	@Autowired
	public TopicServiceImpl(TopicRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Topic> getTopics() {
		Pageable pageWithThreeNews = PageRequest.of(0, 5, Sort.by("topicLastPostTime").descending());

		return repository.findAll(pageWithThreeNews).getContent();
	}

}
