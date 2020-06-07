package ua.cc.lajdev.site.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.site.model.UserRole;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

	@Query("select ur.role from UserRole ur, User u where u.userName=?1 and ur.userId=u.id")
	public List<String> findRoleByUserName(String username);

}
