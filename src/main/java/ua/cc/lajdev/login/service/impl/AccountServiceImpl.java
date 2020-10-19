package ua.cc.lajdev.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.repo.AccountRepository;
import ua.cc.lajdev.login.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository repository;

	@Autowired
	public AccountServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public Account create(Account account) {
		return repository.saveAndFlush(account);
	}

	@Override
	public boolean isPresent(String login) {
		return repository.existsById(login);
	}

	@Override
	public Account getByLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public Account update(Account account) {
		return repository.saveAndFlush(account);
	}

	@Override
	public Account findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Integer countNoGmAccounts() {
		return repository.countNoGmAccounts();
	}

}
