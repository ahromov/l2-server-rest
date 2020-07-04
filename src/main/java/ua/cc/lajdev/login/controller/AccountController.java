package ua.cc.lajdev.login.controller;

import java.util.NoSuchElementException;

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
import ua.cc.lajdev.login.service.impl.mail.ChangePasswordTemplate;
import ua.cc.lajdev.login.service.impl.mail.CreateAccountTemplate;
import ua.cc.lajdev.login.service.impl.mail.MailMessageTemplate;
import ua.cc.lajdev.login.utils.PasswordGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("accounts")
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private PasswordEncoderService encoderService;

	@Autowired
	private MailService mailService;

	@PostMapping("/create")
	public Account registration(@RequestBody UserDto user) {
		Account account = null;

		if ((!user.login.equals("") && !user.email.equals("") && !user.password.equals("")
				&& !user.passwordSecond.equals(""))) {
			account = accountService.findByEmail(user.email);

			if (user.password.equals(user.passwordSecond)) {
				if (account != null) {
					account.setStatus("Email exists");

					return account;
				} else {
					try {
						Account loginAcc = accountService.findByLogin(user.login);
						loginAcc.setStatus("Login exists");

						return loginAcc;
					} catch (NoSuchElementException e) {
						String encodedPassword = encoderService.encodePassword(user.password);

						if (mailService.isCorrectEmailAddress(user.email)) {
							if (mailService.sendMail(user, new CreateAccountTemplate(user))) {
								account = accountService.create(new Account(user.login, encodedPassword, user.email));
								account.setStatus("Success");

								logger.info("New account {" + user.email + "} created");
							}
						} else
							return new Account("Invalid email");
					}
				}
			} else
				account = new Account("No match");
		}

		return account;
	}

	@PostMapping(path = "/login")
	public Account login(@RequestBody UserDto login) {
		Account account = null;

		try {
			account = accountService.findByLogin(login.login);

			String encodedPassword = encoderService.encodePassword(login.password);

			if (account.getPassword().equals(encodedPassword)) {
				account.setStatus("Success");

				return account;
			} else {
				account.setStatus("Incorrect password");

				return account;
			}

		} catch (NoSuchElementException e) {
			logger.error("Account with login {" + login + "} not found");

			account = new Account("Not exists");
		}

		return account;
	}

	@PostMapping("/changePass")
	public Account changePassword(@RequestBody UserDto user) {
		Account account = null;

		if ((!user.login.equals("") && !user.oldPassword.equals("") && !user.newFirstPassword.equals("")
				&& !user.newSecondPassword.equals(""))) {
			try {
				account = accountService.findByLogin(user.login);

				String encodedOldPassword = encoderService.encodePassword(user.oldPassword);

				String encodedNewPassword = encoderService.encodePassword(user.newFirstPassword);

				if (account.getPassword().equals(encodedOldPassword)) {
					if (user.newFirstPassword.equals(user.newSecondPassword)) {
						account.setPassword(encodedNewPassword);
						account = accountService.update(account);
						account.setStatus("Success");

						return account;
					} else {
						account.setStatus("No match");

						return account;
					}
				} else {
					account.setStatus("Invalid pass");

					return account;
				}
			} catch (NoSuchElementException e) {
				logger.error("Cannot update password: account width login {" + user.login + "} not found");
			}
		}

		return account;
	}

	@PostMapping("/restorePass")
	public Account rememberPassword(@RequestBody UserDto user) {
		Account account = null;

		if (!user.login.equals("") && !user.email.equals("")) {
			account = accountService.findByEmail(user.email);

			if (account != null) {
				if (account.getLogin().equals(user.login)) {
					user.password = PasswordGenerator.generateRandomPassword(8);

					String encodedPassword = encoderService.encodePassword(user.password);

					if (mailService.sendMail(user, new ChangePasswordTemplate(user))) {
						account.setPassword(encodedPassword);
						account = accountService.update(account);
						account.setStatus("Success");

						return account;
					}
				} else {
					return new Account("Invalid login"); // Must return new Account instance, beacause it will return correct login
				}
			} else {
				logger.error("Cannot restore password: account with email {" + user.email + "} not found");

				return new Account("Not exists");
			}
		} else
			return new Account("Invalid data");

		return account;
	}

	@GetMapping("/count/all")
	public Integer countAccounts() {
		return accountService.countNoGmAccounts();
	}

	@PostMapping("/sendMess")
	public Account sendMessage(@RequestBody UserDto user) {
		Account account = null;

		if ((!user.email.equals("") && !user.message.equals("") && !user.login.equals(""))) {
			account = accountService.findByEmail(user.email);

			if (account != null) {
				if (account.getLogin().equals(user.login)) {
					user.login = account.getLogin();

					if (mailService.sendMail(user, new MailMessageTemplate(user))) {
						account.setStatus("Success");

						return account;
					}
				} else
					return new Account("Invalid login");
			} else {
				logger.error("Cannot send message: account with email {" + user.email + "} not found");

				return new Account("Email not found");
			}
		} else
			return new Account("Invalid data");

		return account;
	}

}
