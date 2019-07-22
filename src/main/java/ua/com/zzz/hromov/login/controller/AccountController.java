package ua.com.zzz.hromov.login.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.com.zzz.hromov.login.dto.AccountDto;
import ua.com.zzz.hromov.login.model.Account;
import ua.com.zzz.hromov.login.service.AccountService;

@CrossOrigin(origins = "http://hromovl2test.zzz.com.ua", allowedHeaders = "*")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/reg")
    public AccountDto registration(@RequestParam("login") String login, @RequestParam("email") String email,
	    @RequestParam("password") String password, @RequestParam("passwordSecond") String passwordSecond) {
	if ((!login.equals("") && !email.equals("") && !password.equals("") && !passwordSecond.equals(""))
		|| (login != null && email != null && password != null && passwordSecond != null)) {
	    if (password.equals(passwordSecond)) {
		MessageDigest md = null;
		try {
		    md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		} catch (NullPointerException e) {
		    e.printStackTrace();
		}

		String encodedPassword = null;
		try {
		    encodedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}

		try {
		    accountService.get(login);
		    return new AccountDto("Exists");
		} catch (NoSuchElementException e) {
		    e.getMessage();
		    accountService.add(new Account(login, encodedPassword, email));
		    return new AccountDto("Success");
		}
	    } else
		return new AccountDto("No match");
	}

	return new AccountDto("Invalid data");
    }

    @PostMapping("/log")
    public AccountDto login(@RequestParam("login") String login, @RequestParam("password") String password) {
	Account account = null;
	try {
	    account = accountService.get(login);

	    MessageDigest md = null;
	    try {
		md = MessageDigest.getInstance("SHA");
	    } catch (NoSuchAlgorithmException e) {
		e.getMessage();
	    }

	    String encodedPassword = null;
	    try {
		encodedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes("UTF-8")));
	    } catch (UnsupportedEncodingException e) {
		e.getMessage();
	    }

	    if (account.getPassword().equals(encodedPassword)) {
		return new AccountDto("Success");
	    } else
		return new AccountDto("Incorrect password");
	} catch (NoSuchElementException e) {
	    e.getMessage();
	    return new AccountDto("Not exists");
	}
    }

    @PostMapping("/changePass")
    public AccountDto changePassword(@RequestParam("login") String login,
	    @RequestParam("oldPassword") String oldPassword, @RequestParam("newFirstPassword") String newFirstPassword,
	    @RequestParam("newSecondPassword") String newSecondPassword) {
	if ((!login.equals("") && !oldPassword.equals("") && !newFirstPassword.equals("")
		&& !newSecondPassword.equals(""))
		|| (login != null && oldPassword != null && newFirstPassword != null && newSecondPassword != null)) {
	    Account account = null;

	    try {
		account = accountService.get(login);

		MessageDigest md = null;
		try {
		    md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
		    e.getMessage();
		}

		String encodedOldPassword = null;
		try {
		    encodedOldPassword = Base64.getEncoder().encodeToString(md.digest(oldPassword.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		    e.getMessage();
		}

		String encodedNewPassword = null;
		try {
		    encodedNewPassword = Base64.getEncoder()
			    .encodeToString(md.digest(newFirstPassword.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}

		if (account.getPassword().equals(encodedOldPassword)) {
		    if (newFirstPassword.equals(newSecondPassword)) {
			account.setPassword(encodedNewPassword);
			accountService.update(account);
			return new AccountDto("Success");
		    } else {
			return new AccountDto("No match");
		    }
		} else 
		    return new AccountDto("Invalid pass");
	    } catch (NoSuchElementException e) {
		e.getMessage();
		return new AccountDto("Not exists");
	    }
	} else {
	    return new AccountDto("Invalid data");
	}
    }

}
