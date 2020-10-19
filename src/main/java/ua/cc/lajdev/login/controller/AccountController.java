package ua.cc.lajdev.login.controller;

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
		Account account = accountService.findByLogin(user.login);
		if (account == null) {
			validate(user, account);
			account = accountService.create(user.toAccount(encoderService.encodePassword(user.password)));
			mailService.sendMail(user, new MailAccountTemplate(user));
			LOGGER.info("Created new: " + account);
		} else
			throw new AccountExistsException();
	}

	@PostMapping(path = "/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void login(@Valid @RequestBody UserDto user) {
		Account account = accountService.findByLogin(user.login);
		if (account != null) {
			validate(user, account);
			LOGGER.info("Logined: " + account);
		} else
			throw new AccountNotFoundException();
	}

	@PostMapping("/changePass")
	@ResponseStatus(HttpStatus.OK)
	public void changePassword(@Valid @RequestBody UserDto user) {
		Account account = accountService.findByLogin(user.login);
		if (account != null) {
			user.password = user.oldPassword;
			validate(user, account);
			account.setPassword(encoderService.encodePassword(user.password));
			accountService.update(account);
			LOGGER.warn("Password changed: " + account);
		} else
			throw new AccountNotFoundException();
	}

	@PostMapping("/restorePass")
	@ResponseStatus(HttpStatus.OK)
	public void rememberPassword(@Valid @RequestBody UserDto user) {
		Account account = accountService.findByLogin(user.login);
		if (account != null) {
			validate(user, account);
			String newAutoGaneratedPassword = PasswordGenerator.generateRandomPassword(8);
			account.setPassword(encoderService.encodePassword(newAutoGaneratedPassword));
			user.password = newAutoGaneratedPassword;
			accountService.update(account);
			mailService.sendMail(user, new MailPasswordTemplate(user));
			LOGGER.info("Password restored: " + account);
		} else
			throw new AccountNotFoundException();
	}

	@PostMapping("/sendMess")
	@ResponseStatus(HttpStatus.OK)
	public void sendMessage(@Valid @RequestBody UserDto user) {
		Account account = accountService.findByLogin(user.login);
		if (account != null) {
			validate(user, account);
			user.email = account.getEmail();
			mailService.sendMail(user, new MailTemplate(user));
			LOGGER.info("Sended mail from: " + account);
		} else
			throw new AccountNotFoundException();
	}

	@GetMapping("/count/all")
	public Integer countAccounts() {
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
		if (user != null && account != null && user.email != null && !mailService.isCorrectEmailAddress(user.email))
			throw new IncorrectEmailException();
	}

}
