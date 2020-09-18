package ua.cc.lajdev.login.service;

import java.util.Optional;

import ua.cc.lajdev.login.model.Account;

public interface AccountService {

	Account create(Account account);

	Optional<Account> findByLogin(String login);

	Account update(Optional<Account> account);

	Optional<Account> findByEmail(String email);

	Integer countNoGmAccounts();

}
