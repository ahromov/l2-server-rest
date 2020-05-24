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

import ua.cc.lajdev.forum.model.Post;
import ua.cc.lajdev.forum.service.PostService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("forums")
public class ForumController {

	@Autowired
	private PostService postService;

	@GetMapping("/get/posts/last5")
	public List<Post> nextNews() {
		Pageable pageWithThreeNews = PageRequest.of(0, 5, Sort.by("date").descending());

		return postService.getNextPage(pageWithThreeNews).getContent();
	}

}
