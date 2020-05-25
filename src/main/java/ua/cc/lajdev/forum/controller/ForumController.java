package ua.cc.lajdev.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Autowired
	private TopicService topicService;

	@GetMapping("/get/topics/last5")
	public List<Topic> nextNews() {
		Pageable pageWithThreeNews = PageRequest.of(0, 5, Sort.by("lastPostedDate").descending());

		return topicService.getNextPage(pageWithThreeNews).getContent();
	}

}
