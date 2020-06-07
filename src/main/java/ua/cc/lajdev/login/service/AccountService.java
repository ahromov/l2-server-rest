package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.model.Account;

public interface AccountService {

	public Account create(Account account);

	public Account findByLogin(String login);

	public Account update(Account account);

	public Account findByEmail(String email);

	public Long countAll();

}
