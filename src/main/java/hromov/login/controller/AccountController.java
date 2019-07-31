package hromov.login.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hromov.login.dto.AccountDto;
import hromov.login.model.Account;
import hromov.login.service.AccountService;

@CrossOrigin(origins = "http://la2dev.000webhostapp.com", allowedHeaders = "*")
@RestController
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private JavaMailSender javaMailSender;

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
		    logger.error(e.getMessage(), e);
		} catch (NullPointerException e) {
		    logger.error(e.getMessage(), e);
		}

		String encodedPassword = null;
		try {
		    encodedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		    logger.error(e.getMessage(), e);
		}

		try {
		    accountService.get(login);
		    return new AccountDto("Exists");
		} catch (NoSuchElementException e) {
		    accountService.add(new Account(login, encodedPassword, email));
		    logger.error(e.getMessage());
		    if (sendEmail(login, email, password))
			return new AccountDto("Success", true);
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
		logger.error(e.getMessage(), e);
	    }

	    String encodedPassword = null;
	    try {
		encodedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes("UTF-8")));
	    } catch (UnsupportedEncodingException e) {
		logger.error(e.getMessage(), e);
	    }

	    if (account.getPassword().equals(encodedPassword)) {
		return new AccountDto("Success");
	    } else
		return new AccountDto("Incorrect password");
	} catch (NoSuchElementException e) {
	    logger.error(e.getMessage());
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
		    logger.error(e.getMessage(), e);
		}

		String encodedOldPassword = null;
		try {
		    encodedOldPassword = Base64.getEncoder().encodeToString(md.digest(oldPassword.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		    logger.error(e.getMessage(), e);
		}

		String encodedNewPassword = null;
		try {
		    encodedNewPassword = Base64.getEncoder()
			    .encodeToString(md.digest(newFirstPassword.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		    logger.error(e.getMessage(), e);
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
		logger.error(e.getMessage());
		return new AccountDto("Not exists");
	    }
	} else {
	    return new AccountDto("Invalid data");
	}
    }

    private boolean sendEmail(String login, String email, String password) {
	MimeMessage msg = javaMailSender.createMimeMessage();

	MimeMessageHelper helper;
	try {
	    helper = new MimeMessageHelper(msg, true);
	    helper.setTo(email);
	    helper.setSubject("Welcome " + login + "!");
	    helper.setText("<h1>Поздравляем!</h1>" + "<p>Вы были зарегистрированы на нешем сервере!</p>"
		    + "<p>Для начала игры:<br>"
		    + "<a href=\"https://drive.google.com/drive/folders/1a9jqbmIrBIJxJzH0ADN5LaAdtMbB_zKo\">Скачайте</a> патч или полный клиент(он уже настроен)<br>"
		    + "Для входа в игру с клиента используйте ваши:<br>" + "Логин: " + login + "<br>" + "Пароль: "
		    + password + "</p>" + "<p>Приятной игры!:)</p>", true);
	} catch (MessagingException e) {
	    logger.error(e.getMessage());
	    return false;
	}

	javaMailSender.send(msg);

	return true;
    }

}
