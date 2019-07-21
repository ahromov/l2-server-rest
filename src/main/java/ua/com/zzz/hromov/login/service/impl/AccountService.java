package ua.com.zzz.hromov.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.zzz.hromov.login.model.Account;
import ua.com.zzz.hromov.login.repo.AccountRepository;
import ua.com.zzz.hromov.login.service.IAccountService;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public Account add(Account Account) {
	return repository.save(Account);
    }

    @Override
    public void delete(String id) {
	repository.deleteById(id);
    }

    @Override
    public Account get(String login) {
	return repository.findById(login).get();
    }

    @Override
    public Account update(Account account) {
	return repository.saveAndFlush(account);
    }

    @Override
    public Account getByEmail(String email) {
	return repository.findByEmail(email);
    }

    @Override
    public List<Account> getAll() {
	return repository.findAll();
    }

}
