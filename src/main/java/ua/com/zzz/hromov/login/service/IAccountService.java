package ua.com.zzz.hromov.login.service;

import java.util.List;

import ua.com.zzz.hromov.login.model.Account;

public interface IAccountService {

    Account add(Account bank);

    void delete(String id);

    Account get(String id);

    Account update(Account bank);

    List<Account> getAll();

    Account getByEmail(String email);

}
