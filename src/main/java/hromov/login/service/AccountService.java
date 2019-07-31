package hromov.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.login.model.Account;
import hromov.login.repo.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account add(Account account) {
	return repository.save(account);
    }

    public Account get(String login) {
	return repository.findById(login).get();
    }

    public Account update(Account account) {
	return repository.saveAndFlush(account);
    }

    public Account getByEmail(String email) {
	return repository.findByEmail(email);
    }

}
