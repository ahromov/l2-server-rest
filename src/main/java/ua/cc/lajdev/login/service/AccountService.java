package ua.cc.lajdev.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.repo.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account create(Account account) {
	return repository.save(account);
    }

    public Account find(String login) {
	return repository.findById(login).get();
    }

    public Account update(Account account) {
	return repository.saveAndFlush(account);
    }

    public Account findByEmail(String email) {
	return repository.findByEmail(email);
    }

    public Long countAccounts() {
	return repository.countAccounts();
    }

}
