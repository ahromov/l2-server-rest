package ua.com.zzz.hromov.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.zzz.hromov.login.model.Account;
import ua.com.zzz.hromov.login.repo.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account add(Account Account) {
	return repository.save(Account);
    }

    public void delete(String id) {
	repository.deleteById(id);
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

    public List<Account> getAll() {
	return repository.findAll();
    }

}
