package ua.cc.lajdev.site.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ua.cc.lajdev.site.model.News;
import ua.cc.lajdev.site.service.NewsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("news")
public class NewsController {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	@PostMapping("/add")
	public News addNew(@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("image") MultipartFile image) {
		News news = null;

		byte[] imContent = null;

		try {
			imContent = image.getBytes();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		news = newsService.create(new News(title, text, new Date(), imContent));

		news.setStatus("Success");

		return news;
	}

	@GetMapping("/get/{id}")
	public News getById(@PathVariable("id") Long id) {
		return newsService.getById(id);
	}

	@GetMapping("/get/pages")
	public Integer getNewsIds() {
		Pageable pageParam = PageRequest.of(0, 3);

		return newsService.getNextPage(pageParam).getTotalPages();
	}

	@GetMapping("/get/pages/{pageNumber}")
	public List<News> nextNews(@PathVariable("pageNumber") Integer pageNumber) {
		Pageable pageWithThreeNews = PageRequest.of(pageNumber, 3, Sort.by("id").descending());

		return newsService.getNextPage(pageWithThreeNews).getContent();
	}

	@GetMapping("/get/lastThree")
	public List<News> getLastThree() {
		List<News> list = newsService.getAll();
		Collections.reverse(list);

		return list.subList(0, 3);
	}

}
