package ua.cc.lajdev.login.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.dto.UserDto;
import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.service.AccountService;
import ua.cc.lajdev.login.service.MailService;
import ua.cc.lajdev.login.service.PasswordEncoderService;
import ua.cc.lajdev.login.service.impl.mail.MailPasswordTemplate;
import ua.cc.lajdev.login.service.impl.mail.MailAccountTemplate;
import ua.cc.lajdev.login.service.impl.mail.MailTemplate;
import ua.cc.lajdev.login.utils.PasswordGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("accounts")
public class AccountController {

	private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

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
	public Account registration(@RequestBody UserDto user) {
		if ((!user.login.equals("") && !user.email.equals("") && !user.password.equals("")
				&& !user.passwordSecond.equals(""))) {
			Optional<Account> account = accountService.findByLogin(user.login);

			if (!account.isPresent()) {
				if (user.password.equals(user.passwordSecond)) {
					if (mailService.isCorrectEmailAddress(user.email)) {
						account = Optional.of(accountService.create(new Account(user.login,
								encoderService.encodePassword(user.password), user.email, "Success")));

						mailService.sendMail(user, new MailAccountTemplate(user));

						return account.get();
					} else
						return new Account("Invalid email");
				} else {
					return new Account("No match");
				}
			} else {
				account.get().setStatus("Login exists");

				return account.get();
			}
		}

		return new Account("Invalid data");
	}

	@PostMapping(path = "/login")
	public Account login(@RequestBody UserDto login) {
		if (login.login != null && login.password != null) {
			Optional<Account> account = accountService.findByLogin(login.login);

			if (account.isPresent()) {
				String encodedPassword = encoderService.encodePassword(login.password);

				if (account.get().getPassword().equals(encodedPassword)) {
					account.get().setStatus("Success");

					return account.get();
				} else {
					account.get().setStatus("Incorrect password");

					return account.get();
				}
			} else {
				LOGGER.error("Account with login {" + login + "} not found");

				return new Account("Not exists");
			}
		}

		return new Account("Invalid data");
	}

	@PostMapping("/changePass")
	public Account changePassword(@RequestBody UserDto user) {
		if ((!user.login.equals("") && !user.oldPassword.equals("") && !user.newFirstPassword.equals("")
				&& !user.newSecondPassword.equals(""))) {
			Optional<Account> account = accountService.findByLogin(user.login);

			if (account.isPresent()) {
				String encodedOldPassword = encoderService.encodePassword(user.oldPassword);

				String encodedNewPassword = encoderService.encodePassword(user.newFirstPassword);

				if (account.get().getPassword().equals(encodedOldPassword)) {
					if (user.newFirstPassword.equals(user.newSecondPassword)) {
						account.get().setPassword(encodedNewPassword);
						accountService.update(account);
						account.get().setStatus("Success");

						return account.get();
					} else {
						account.get().setStatus("No match");

						return account.get();
					}
				} else {
					account.get().setStatus("Invalid pass");

					return account.get();
				}
			}
		}

		return new Account("Invalid data");
	}

	@PostMapping("/restorePass")
	public Account rememberPassword(@RequestBody UserDto user) {
		if (!user.login.equals("") && !user.email.equals("")) {
			Optional<Account> account = accountService.findByLogin(user.login);

			if (account.isPresent()) {
				if (account.get().getLogin().equals(user.login)) {
					user.password = PasswordGenerator.generateRandomPassword(8);

					String encodedPassword = encoderService.encodePassword(user.password);

					account.get().setPassword(encodedPassword);
					accountService.update(account);
					account.get().setStatus("Success");

					mailService.sendMail(user, new MailPasswordTemplate(user));

					return account.get();
				} else {
					return new Account("Invalid login");
				}
			} else {
				return new Account("Not exists");
			}
		} else
			return new Account("Invalid data");
	}

	@GetMapping("/count/all")
	public Integer countAccounts() {
		return accountService.countNoGmAccounts();
	}

	@PostMapping("/sendMess")
	public Account sendMessage(@RequestBody UserDto user) {
		if ((!user.email.equals("") && !user.message.equals("") && !user.login.equals(""))) {
			Optional<Account> account = accountService.findByLogin(user.login);

			if (account.isPresent()) {
				if (account.get().getLogin().equals(user.login)) {
					user.login = account.get().getLogin();

					account.get().setStatus("Success");

					mailService.sendMail(user, new MailTemplate(user));

					return account.get();
				} else
					return new Account("Invalid login");
			} else {
				return new Account("Email not found");
			}
		} else
			return new Account("Invalid data");
	}

}
