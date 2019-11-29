package hromov.forum.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.forum.model.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    Page<Section> findByCategoryId(Long categoryId, Pageable pageable);

    Optional<Section> findByIdAndCategoryId(Long id, Long categoryId);

}
