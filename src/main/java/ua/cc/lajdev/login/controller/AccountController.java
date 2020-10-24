package ua.cc.lajdev.login.controller;

import java.util.NoSuchElementException;

import javax.persistence.NonUniqueResultException;
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
import ua.cc.lajdev.common.controller.exceptions.NonUniqueEmailException;
import ua.cc.lajdev.common.controller.exceptions.PasswordsNotMatchException;
import ua.cc.lajdev.common.dto.user.ChangePasswordDto;
import ua.cc.lajdev.common.dto.user.LoginDto;
import ua.cc.lajdev.common.dto.user.MessageDto;
import ua.cc.lajdev.common.dto.user.RegistrationDto;
import ua.cc.lajdev.common.dto.user.RestorePasswordDto;
import ua.cc.lajdev.common.dto.user.UserDto;
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
	public void registration(@Valid @RequestBody RegistrationDto user) {
		try {
			accountService.findByLogin(user.login);
			throw new AccountExistsException();
		} catch (NoSuchElementException e) {
			validate(user, null);
			Account account = accountService.create(user.toAccount(encoderService.encodePassword(user.password)));
			mailService.sendMail(account, new MailAccountTemplate(user));
		}
	}

	@PostMapping(path = "/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void login(@Valid @RequestBody LoginDto user) {
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
	public void changePassword(@Valid @RequestBody ChangePasswordDto user) {
		try {
			Account account = accountService.findByLogin(user.login);
			validate(user, account);
			account.setPassword(user.newPassword);
			mailService.sendMail(account, new MailPasswordTemplate(account));
			account.setPassword(encoderService.encodePassword(user.newPassword));
			accountService.update(account);
			LOGGER.warn("Password changed: " + account);
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException();
		}
	}

	@PostMapping("/restorePass")
	@ResponseStatus(HttpStatus.OK)
	public void restorePassword(@Valid @RequestBody RestorePasswordDto user) {
		try {
			Account account = accountService.findByLogin(user.login);
			validate(user, account);
			String newAutoGaneratedPassword = PasswordGenerator.generateRandomPassword(8);
			account.setPassword(newAutoGaneratedPassword);
			mailService.sendMail(account, new MailPasswordTemplate(account));
			account.setPassword(encoderService.encodePassword(newAutoGaneratedPassword));
			accountService.update(account);
			LOGGER.info("Password restored: " + account);
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException();
		}
	}

	@PostMapping("/sendMess")
	@ResponseStatus(HttpStatus.OK)
	public void sendMessage(@Valid @RequestBody MessageDto user) {
		try {
			Account account = accountService.findByLogin(user.login);
			validate(user, account);
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

	private <T extends UserDto> void validate(T user, Account account) {
		if (user instanceof RegistrationDto && account == null
				&& !((RegistrationDto) user).password.equals(((RegistrationDto) user).repeatedPassword))
			throw new PasswordsNotMatchException();
		if (user instanceof RegistrationDto && account == null
				&& !mailService.isCorrectDomainEmailAddress(((RegistrationDto) user).email))
			throw new IncorrectEmailException();
		if (user instanceof LoginDto
				&& !encoderService.encodePassword(((LoginDto) user).password).equals(account.getPassword()))
			throw new IncorrectPasswordException();
		if (user instanceof ChangePasswordDto
				&& !((ChangePasswordDto) user).newPassword.equals(((ChangePasswordDto) user).newRepeatedPassword))
			throw new NewPasswordsNotMatchException();
		if (user instanceof RestorePasswordDto && account != null
				&& !(((RestorePasswordDto) user).email).equals(account.getEmail()))
			throw new IncorrectEmailException();
	}

}
