package ua.cc.lajdev.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.repo.AccountRepository;
import ua.cc.lajdev.login.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;

	public Account create(Account account) {
		return repository.save(account);
	}

	@Override
	public Account findByLogin(String login) {
		return repository.findById(login).get();
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
	public Long countAll() {
		return repository.countNotGmAccounts();
	}

}