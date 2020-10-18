package ua.cc.lajdev.login.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.common.controller.exceptions.AccountExistsException;
import ua.cc.lajdev.common.controller.exceptions.AccountNotFoundException;
import ua.cc.lajdev.common.controller.exceptions.IncorrectEmailException;
import ua.cc.lajdev.common.controller.exceptions.IncorrectPasswordException;
import ua.cc.lajdev.common.controller.exceptions.InvalidDatasException;
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
			if (isInputedPasswordsEquals(user)) {
				if (mailService.isCorrectEmailAddress(user.email)) {
					user.password = encoderService.encodePassword(user.password);
					account = accountService.create(user.toAccount());
					mailService.sendMail(user, new MailAccountTemplate(user));
					LOGGER.info("Created new: " + account);
				} else
					throw new IncorrectEmailException();
			} else
				throw new PasswordsNotMatchException();
		} else
			throw new AccountExistsException();
	}

	@PostMapping(path = "/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void login(@Valid @RequestBody UserDto login) {
		Account account = accountService.findByLogin(login.login);
		if (account != null) {
			if (isUserPasswordValid(login, account)) {
				LOGGER.info("Logined: " + account);
			} else
				throw new IncorrectPasswordException();
		} else
			throw new AccountNotFoundException();
	}

	@PostMapping("/changePass")
	@ResponseStatus(HttpStatus.OK)
	public void changePassword(@Valid @RequestBody UserDto user) {
		Account account = accountService.findByLogin(user.login);
		if (account != null) {
			updateAccount(user, account);
			LOGGER.warn("Password changed: " + account);
		}
	}

	private void updateAccount(UserDto user, Account account) {
		user.password = user.oldPassword;
		if (isUserPasswordValid(user, account)) {
			updatePassword(user, account);
		} else
			throw new IncorrectPasswordException();
	}

	private boolean isUserPasswordValid(UserDto user, Account account) {
		String encodedPassword = encoderService.encodePassword(user.password);
		if (account.getPassword().equals(encodedPassword)) {
			return true;
		}
		return false;
	}

	private void updatePassword(UserDto user, Account account) {
		user.password = user.newFirstPassword;
		user.repeatedPassword = user.newSecondPassword;
		if (isInputedPasswordsEquals(user)) {
			account.setPassword(encoderService.encodePassword(user.password));
			accountService.update(account);
		} else
			throw new PasswordsNotMatchException();
	}

	private boolean isInputedPasswordsEquals(UserDto user) {
		if (user.password.equals(user.repeatedPassword))
			return true;
		return false;
	}

	@PostMapping("/restorePass")
	@ResponseStatus(HttpStatus.OK)
	public void rememberPassword(@Valid @RequestBody UserDto user) {
		Account account = accountService.findByLogin(user.login);
		if (account != null) {
			if (user.email.equals(account.getEmail())) {
				account.setPassword(encoderService.encodePassword(PasswordGenerator.generateRandomPassword(8)));
				accountService.update(account);
				mailService.sendMail(user, new MailPasswordTemplate(user));
				LOGGER.info("Password restored: " + account);
			} else
				throw new IncorrectEmailException();
		} else
			throw new AccountNotFoundException();
	}

	@PostMapping("/sendMess")
	@ResponseStatus(HttpStatus.OK)
	public void sendMessage(@Valid @RequestBody UserDto user) {
		Account account = accountService.findByLogin(user.login);
		if (account != null) {
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

}
