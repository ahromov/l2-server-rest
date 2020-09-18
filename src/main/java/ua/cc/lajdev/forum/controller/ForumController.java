package ua.cc.lajdev.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.forum.model.Topic;
import ua.cc.lajdev.forum.service.TopicService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("forums")
public class ForumController {

	private final TopicService topicService;

	@Autowired
	public ForumController(TopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping("/get/topics/last5")
	public List<Topic> getTopics() {
		return topicService.getTopics();
	}

}
