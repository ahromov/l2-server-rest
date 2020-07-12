package ua.cc.lajdev.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.site.model.News;
import ua.cc.lajdev.site.repo.NewsRepository;
import ua.cc.lajdev.site.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	private final NewsRepository repository;

	@Autowired
	public NewsServiceImpl(NewsRepository repository) {
		this.repository = repository;
	}

	@Override
	public News create(News news) {
		return repository.save(news);
	}

	@Override
	public News getById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<News> getNewsPage(Integer number) {
		Pageable pageWithThreeNews = PageRequest.of(number, 3, Sort.by("date").descending());

		return repository.findAll(pageWithThreeNews).getContent();
	}

	@Override
	public List<News> getAll() {
		return repository.findAll();
	}

	@Override
	public Integer countAllPage() {
		Pageable pageParam = PageRequest.of(0, 3);

		return repository.findAll(pageParam).getTotalPages();
	}

}
