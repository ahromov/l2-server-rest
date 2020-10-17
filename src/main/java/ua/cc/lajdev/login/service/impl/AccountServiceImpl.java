package ua.cc.lajdev.login.service.impl;

import java.util.Optional;

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

	public Account create(Account account) {
		return repository.save(account);
	}

	@Override
	public Account findByLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public Account update(Optional<Account> account) {
		return repository.save(account.get());
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
