package ua.cc.lajdev.site.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ua.cc.lajdev.site.dto.NewsDto;
import ua.cc.lajdev.site.model.News;
import ua.cc.lajdev.site.service.NewsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("news")
public class NewsController {

	private final NewsService newsService;

	@Autowired
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@PostMapping(path = "/add", consumes = "multipart/form-data")
	@ResponseStatus(code = HttpStatus.OK)
	public News addNew(@RequestPart("newsDto") @Valid NewsDto newsDto, @RequestPart("image") MultipartFile image) {
		newsDto.image = image;
		return newsService.create(newsDto.toNews());
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
