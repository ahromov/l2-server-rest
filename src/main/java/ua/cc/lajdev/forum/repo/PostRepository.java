package ua.cc.lajdev.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.forum.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
