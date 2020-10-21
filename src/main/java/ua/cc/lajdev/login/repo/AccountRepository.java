package ua.cc.lajdev.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.login.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("select count(a) from Account a")
	Integer countNoGmAccounts();

}
