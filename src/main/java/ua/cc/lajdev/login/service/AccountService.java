package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.model.Account;

public interface AccountService {

	Account create(Account account);

	Account findByLogin(String login);

	Account update(Account account);

	Integer countNoGmAccounts();

	Account findByEmail(String email);

}
