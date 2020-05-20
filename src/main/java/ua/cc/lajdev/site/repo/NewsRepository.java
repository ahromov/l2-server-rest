package ua.cc.lajdev.site.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.site.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

	@Query(value = "SELECT id FROM news ORDER BY id DESC", nativeQuery = true)
	List<Long> getNewsIds();

}
