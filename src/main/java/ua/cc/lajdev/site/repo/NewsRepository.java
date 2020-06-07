package ua.cc.lajdev.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.site.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
