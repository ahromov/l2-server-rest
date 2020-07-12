package ua.cc.lajdev.site.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

	private final NewsService newsService;

	@Autowired
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@PostMapping("/add")
	public News addNew(@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("image") MultipartFile image) {
		News news = null;

		try {
			news = newsService.create(new News(title, text, new Date(), image.getBytes()));
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		news.setStatus("Success");

		return news;
	}

	@GetMapping("/get/{id}")
	public News getById(@PathVariable("id") Long id) {
		return newsService.getById(id);
	}

	@GetMapping("/pages/count")
	public Integer getNewsIds() {
		return newsService.countAllPage();
	}

	@GetMapping("/get/pages/{pageNumber}")
	public List<News> nextNews(@PathVariable("pageNumber") Integer pageNumber) {
		return newsService.getNewsPage(pageNumber);
	}

}
