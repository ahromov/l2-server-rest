package hromov.forum.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.forum.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> getAllByForumId(Long forumId, Pageable page);

    Optional<Comment> findByIdAndForumId(Long id, Long forumId);

}
