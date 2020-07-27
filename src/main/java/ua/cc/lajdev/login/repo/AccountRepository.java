package ua.cc.lajdev.login.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.login.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	Optional<Account> findByEmail(String email);

	@Query("select count(a) from Account a")
	Integer countNoGmAccounts();

}
