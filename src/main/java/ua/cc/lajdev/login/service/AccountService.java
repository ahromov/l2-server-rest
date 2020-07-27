package ua.cc.lajdev.login.service;

import java.util.Optional;

import ua.cc.lajdev.login.model.Account;

public interface AccountService {

	public Account create(Account account);

	public Optional<Account> findByLogin(String login);

	public Account update(Optional<Account> account);

	public Optional<Account> findByEmail(String email);

	public Integer countNoGmAccounts();

}
