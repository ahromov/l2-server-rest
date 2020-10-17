package ua.cc.lajdev.login.controller;

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

import ua.cc.lajdev.login.controller.exception.AccountExistsException;
import ua.cc.lajdev.login.controller.exception.AccountNotExistsException;
import ua.cc.lajdev.login.controller.exception.IncorrectEmailException;
import ua.cc.lajdev.login.controller.exception.IncorrectPasswordException;
import ua.cc.lajdev.login.controller.exception.InvalidDataException;
import ua.cc.lajdev.login.controller.exception.PasswordsNotMatchException;
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
	public void registration(@RequestBody UserDto user) {
		if ((!user.login.equals("") && !user.email.equals("") && !user.password.equals("")
				&& !user.passwordSecond.equals(""))) {
			Account account = accountService.findByLogin(user.login);
			if (account == null) {
				if (isUserPasswordsEquals(user)) {
					if (isEmailCorrectSendNotification(user)) {
						user.password = encoderService.encodePassword(user.password);
						account = accountService.create(user.toAccount());
						LOGGER.info("Created new: " + account);
					} else
						throw new IncorrectEmailException();
				} else
					throw new PasswordsNotMatchException();
			} else
				throw new AccountExistsException();
		} else
			throw new InvalidDataException();
	}

	private boolean isEmailCorrectSendNotification(UserDto user) {
		if (mailService.isCorrectEmailAddress(user.email)) {
			mailService.sendMail(user, new MailAccountTemplate(user));
			return true;
		}
		return false;
	}

	@PostMapping(path = "/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void login(@RequestBody UserDto login) {
		if (login.login != null && login.password != null) {
			Account account = accountService.findByLogin(login.login);
			if (account != null) {
				if (isUserPasswordValid(login, account)) {
					LOGGER.info("Logined: " + account);
				} else
					throw new IncorrectPasswordException();
			} else
				throw new AccountNotExistsException();
		} else
			throw new InvalidDataException();
	}

	@PostMapping("/changePass")
	@ResponseStatus(HttpStatus.OK)
	public void changePassword(@RequestBody UserDto user) {
		if ((!user.login.equals("") && !user.oldPassword.equals("") && !user.newFirstPassword.equals("")
				&& !user.newSecondPassword.equals(""))) {
			Account account = accountService.findByLogin(user.login);
			if (account != null) {
				updateAccount(user, account);
				LOGGER.warn("Password changed: " + account);
			}
		} else
			throw new InvalidDataException();
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
		user.passwordSecond = user.newSecondPassword;
		if (isUserPasswordsEquals(user)) {
			account.setPassword(encoderService.encodePassword(user.password));
			accountService.update(account);
		} else
			throw new PasswordsNotMatchException();
	}

	private boolean isUserPasswordsEquals(UserDto user) {
		if (user.password.equals(user.passwordSecond))
			return true;
		return false;
	}

	@PostMapping("/restorePass")
	@ResponseStatus(HttpStatus.OK)
	public void rememberPassword(@RequestBody UserDto user) {
		if (!user.login.equals("") && !user.email.equals("")) {
			Account account = accountService.findByLogin(user.login);
			if (account != null) {
				user.password = PasswordGenerator.generateRandomPassword(8);
				account.setPassword(encoderService.encodePassword(user.password));
				accountService.update(account);
				mailService.sendMail(user, new MailPasswordTemplate(user));
				LOGGER.info("Password restored: " + account);
			} else
				throw new AccountNotExistsException();
		} else {
			throw new InvalidDataException();
		}
	}

	@GetMapping("/count/all")
	public Integer countAccounts() {
		return accountService.countNoGmAccounts();
	}

	@PostMapping("/sendMess")
	@ResponseStatus(HttpStatus.OK)
	public void sendMessage(@RequestBody UserDto user) {
		if (!user.email.equals("") && !user.login.equals("") && !user.message.equals("")) {
			Account account = accountService.findByLogin(user.login);
			if (account != null) {
				mailService.sendMail(user, new MailTemplate(user));
				LOGGER.info("Sended mail from: " + account);
			} else
				throw new AccountNotExistsException();
		} else
			throw new InvalidDataException();
	}

}
