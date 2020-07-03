package ua.cc.lajdev.site.service;

import java.util.List;

import ua.cc.lajdev.site.model.News;

public interface NewsService {

	public News create(News news);

	public News getById(Long id);

	public Integer countAllPage();

	public List<News> getNewsPage(Integer pageNumber);

	public List<News> getAll();

}
