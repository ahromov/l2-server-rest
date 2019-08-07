package hromov.login.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hromov.login.dto.AccountDto;
import hromov.login.dto.CountAccountsDto;
import hromov.login.model.Account;
import hromov.login.service.AccountService;
import hromov.login.utils.PasswordGenerator;

@CrossOrigin(origins = "https://93.170.116.143", allowedHeaders = "*")
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
		    if (sendRegistrationEmail(login, email, password))
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

    @PostMapping("/rem")
    public AccountDto rememberPassword(@RequestParam("login") String login, @RequestParam("email") String email) {
	if (!login.equals("") && !email.equals("")) {
	    Account account = null;

	    try {
		account = accountService.get(login);
	    } catch (NoSuchElementException e) {
		logger.error(e.getMessage());
		return new AccountDto("Not exists");
	    }

	    if (account.getEmail().equals(email)) {
		String newPassword = PasswordGenerator.generateRandomPassword(8);

		MessageDigest md = null;
		try {
		    md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
		    logger.error(e.getMessage(), e);
		}

		String encodedPassword = null;
		try {
		    encodedPassword = Base64.getEncoder().encodeToString(md.digest(newPassword.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		    logger.error(e.getMessage(), e);
		}

		account.setPassword(encodedPassword);
		accountService.update(account);
		sendReminderEmail(login, email, newPassword);
		return new AccountDto("Success", true);
	    } else
		return new AccountDto("Invalid email");
	}

	return new AccountDto("Invalid data");
    }

    @GetMapping("/accCount")
    public CountAccountsDto countAccounts() {
	return new CountAccountsDto(accountService.countAccounts());
    }

    private boolean sendRegistrationEmail(String login, String email, String password) {
	MimeMessage msg = javaMailSender.createMimeMessage();

	MimeMessageHelper helper;
	try {
	    helper = new MimeMessageHelper(msg, true);
	    helper.setFrom("hromov.la2dev@gmail.com");
	    helper.setTo(email);
	    helper.setSubject("Welcome " + login + "!");

	    String messageFile = "message.html";
	    List<String> list = new ArrayList<String>();
	    StringBuilder message = new StringBuilder();

	    try (Stream<String> stream = Files.lines(Paths.get(messageFile))) {
		list = stream.collect(Collectors.toList());
	    } catch (IOException e) {
		logger.error(e.getMessage());
	    }

	    list.forEach(s -> message.append(s.trim()));

	    message.append("<h2>Учетные данные для входа в игру:</h2><br><p>Логин: " + login + "<br>Пароль: " + password
		    + "</p>");

	    helper.setText(message.toString(), true);
	} catch (MessagingException e) {
	    logger.error(e.getMessage());
	    return false;
	}

	javaMailSender.send(msg);

	return true;
    }

    private boolean sendReminderEmail(String login, String email, String password) {
	MimeMessage msg = javaMailSender.createMimeMessage();

	MimeMessageHelper helper;
	try {
	    helper = new MimeMessageHelper(msg, true);
	    helper.setFrom("hromov.la2dev@gmail.com");
	    helper.setTo(email);
	    helper.setSubject("Смена пароля... " + login + "!");
	    helper.setText("<h1>Пароль успешно изменен!</h1>" + "<p>Ваш новый пароль: " + password
		    + "<br>Изменить его вы можете в личном кабинете.</p>", true);
	} catch (MessagingException e) {
	    logger.error(e.getMessage());
	    return false;
	}

	javaMailSender.send(msg);

	return true;
    }

}
