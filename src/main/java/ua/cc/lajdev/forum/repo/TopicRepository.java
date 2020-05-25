package ua.cc.lajdev.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.forum.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
