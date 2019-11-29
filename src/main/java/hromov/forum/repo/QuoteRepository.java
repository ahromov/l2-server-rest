package hromov.forum.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.forum.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Page<Quote> getAllByCommentId(Long commentId, Pageable page);

    Optional<Quote> findByIdAndCommentId(Long id, Long commentId);

}
