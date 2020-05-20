package ua.cc.lajdev.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.site.model.News;
import ua.cc.lajdev.site.repo.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository repository;

	public News create(News news) {
		return repository.save(news);
	}

	public News getById(Long id) {
		return repository.findById(id).get();
	}

	public List<Long> getNewsIds() {
		return repository.getNewsIds();
	}

	public List<News> getAll() {
		return repository.findAll();
	}

}
