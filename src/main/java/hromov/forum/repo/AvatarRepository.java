package hromov.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.forum.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, String> {

}
