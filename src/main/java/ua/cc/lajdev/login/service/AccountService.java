package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.model.Account;

public interface AccountService {

	Account create(Account account);

	boolean isPresent(String login);

	Account getByLogin(String login);

	Account update(Account account);

	Account findByEmail(String email);

	Integer countNoGmAccounts();

}
