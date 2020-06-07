package ua.cc.lajdev.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.cc.lajdev.login.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("select b from accounts b where b.email = :email")
	Account findByEmail(@Param("email") String email);

	@Query("select count(*) from accounts where accessLevel = 0")
	Long countNotGmAccounts();

}
