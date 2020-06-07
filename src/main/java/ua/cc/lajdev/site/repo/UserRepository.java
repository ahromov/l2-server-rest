package ua.cc.lajdev.site.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.site.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUserName(String username);

}
