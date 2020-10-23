package ua.cc.lajdev.login.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.common.controller.exceptions.AccountExistsException;
import ua.cc.lajdev.common.controller.exceptions.AccountNotFoundException;
import ua.cc.lajdev.common.controller.exceptions.IncorrectEmailException;
import ua.cc.lajdev.common.controller.exceptions.IncorrectPasswordException;
import ua.cc.lajdev.common.controller.exceptions.NewPasswordsNotMatchException;
import ua.cc.lajdev.common.controller.exceptions.PasswordsNotMatchException;
import ua.cc.lajdev.login.dto.user.UserDto;
import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.service.AccountService;
import ua.cc.lajdev.login.service.MailService;
import ua.cc.lajdev.login.service.PasswordEncoderService;
import ua.cc.lajdev.login.service.impl.mail.MailAccountTemplate;
import ua.cc.lajdev.login.service.impl.mail.MailPasswordTemplate;
import ua.cc.lajdev.login.service.impl.mail.MailTemplate;
import ua.cc.lajdev.login.utils.PasswordGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("accounts")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	private final AccountService accountService;
	private final PasswordEncoderService encoderService;
	private final MailService mailService;

	@Autowired
	public AccountController(AccountService accountService, PasswordEncoderService encoderService,
			MailService mailService) {
		this.accountService = accountService;
		this.encoderService = encoderService;
		this.mailService = mailService;
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void registration(@Valid @RequestBody UserDto user) {
		try {
			accountService.findByLogin(user.login);
			throw new AccountExistsException();
		} catch (NoSuchElementException e) {
			validate(user, null);
			Account account = accountService.create(user.toAccount(encoderService.encodePassword(user.password)));
			mailService.sendMail(account, new MailAccountTemplate(user));
			LOGGER.info("Created new: " + account);
		}
	}

	@PostMapping(path = "/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void login(@Valid @RequestBody UserDto user) {
		try {
			Account account = accountService.findByLogin(user.login);
			validate(user, account);
			LOGGER.info("Logined: " + account);
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException();
		}
	}

	@PostMapping("/changePass")
	@ResponseStatus(HttpStatus.OK)
	public void changePassword(@Valid @RequestBody UserDto user) {
		try {
			Account account = accountService.findByLogin(user.login);
			user.password = user.oldPassword;
			validate(user, account);
			account.setPassword(encoderService.encodePassword(user.newPassword));
			accountService.update(account);
			user.password = user.newPassword;
			user.email = account.getEmail();
			mailService.sendMail(account, new MailPasswordTemplate(user));
			LOGGER.warn("Password changed: " + account);
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException();
		}
	}

	@PostMapping("/restorePass")
	@ResponseStatus(HttpStatus.OK)
	public void restorePassword(@Valid @RequestBody UserDto user) {
		try {
			Account account = accountService.findByLogin(user.login);
			validate(user, account);
			String newAutoGaneratedPassword = PasswordGenerator.generateRandomPassword(8);
			account.setPassword(encoderService.encodePassword(newAutoGaneratedPassword));
			user.password = newAutoGaneratedPassword;
			accountService.update(account);
			mailService.sendMail(account, new MailPasswordTemplate(user));
			LOGGER.info("Password restored: " + account);
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException();
		}
	}

	@PostMapping("/sendMess")
	@ResponseStatus(HttpStatus.OK)
	public void sendMessage(@Valid @RequestBody UserDto user) {
		try {
			Account account = accountService.findByLogin(user.login);
			validate(user, account);
			user.email = account.getEmail();
			mailService.sendMail(account, new MailTemplate(user));
			LOGGER.info("Sended mail from: " + account);
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException();
		}
	}

	@GetMapping("/count/all")
	public Integer countAllAccounts() {
		return accountService.countNoGmAccounts();
	}

	private void validate(UserDto user, Account account) {
		if (user != null && user.repeatedPassword != null && !user.password.equals(user.repeatedPassword))
			throw new PasswordsNotMatchException();
		if (user != null && user.newPassword != null && user.newRepeatedPassword != null
				&& !user.newPassword.equals(user.newRepeatedPassword))
			throw new NewPasswordsNotMatchException();
		if (user != null && account != null && user.password != null
				&& !account.getPassword().equals(encoderService.encodePassword(user.password)))
			throw new IncorrectPasswordException();
		if (user != null && account != null && user.email != null
				&& !mailService.isCorrectDomainEmailAddress(user.email))
			throw new IncorrectEmailException();
	}

}
