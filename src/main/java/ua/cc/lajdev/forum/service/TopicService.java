package ua.cc.lajdev.forum.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.cc.lajdev.forum.model.Topic;

public interface TopicService {

	public Page<Topic> getPage(Pageable pageable);

}
