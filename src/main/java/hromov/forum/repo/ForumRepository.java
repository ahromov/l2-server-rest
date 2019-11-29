package hromov.forum.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.forum.model.Forum;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

    Page<Forum> getAllBySectionId(Long id, Pageable page);

    Optional<Forum> findByIdAndSectionId(Long id, Long sectionId);

}
