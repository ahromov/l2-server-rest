package hromov.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.forum.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
