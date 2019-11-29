package hromov.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.forum.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

}
