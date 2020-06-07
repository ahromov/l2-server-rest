package ua.cc.lajdev.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.site.model.News;
import ua.cc.lajdev.site.repo.NewsRepository;
import ua.cc.lajdev.site.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository repository;

	@Override
	public News create(News news) {
		return repository.save(news);
	}

	@Override
	public News getById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Page<News> getPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<News> getAll() {
		return repository.findAll();
	}

}
