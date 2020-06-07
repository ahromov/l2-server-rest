package ua.cc.lajdev.site.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.cc.lajdev.site.model.News;

public interface NewsService {

	public News create(News news);

	public News getById(Long id);

	public Page<News> getPage(Pageable pageable);

	public List<News> getAll();

}
