package ua.cc.lajdev.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.dto.RegistrationUserDto;
import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.service.AccountService;
import ua.cc.lajdev.login.service.MailService;
import ua.cc.lajdev.login.service.PasswordEncoderService;
import ua.cc.lajdev.login.service.impl.mail.MailAccountTemplate;

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
	@ResponseStatus(HttpStatus.CREATED)
	public void registration(@RequestBody RegistrationUserDto user) {
		if ((!user.login.equals("") && !user.email.equals("") && !user.password.equals("")
				&& !user.passwordSecond.equals(""))) {
			Account account = accountService.findByLogin(user.login);
			if (account != null) {
				if (isPasswordsEquals(user)) {
					if (isEmailCorrectSendNotification(user)) {
						accountService.create(
								new Account(user.login, encoderService.encodePassword(user.password), user.email));
					} else
						throw new IncorrectEmailException();
				} else
					throw new PasswordsNotMatchException();
			} else {
				throw new AccountPresentException();
			}
		} else
			throw new InvalidDataException();
	}

	private boolean isPasswordsEquals(RegistrationUserDto user) {
		if (user.password.equals(user.passwordSecond))
			return true;
		return false;
	}

	private boolean isEmailCorrectSendNotification(RegistrationUserDto user) {
		if (mailService.isCorrectEmailAddress(user.email)) {
			mailService.sendMail(user, new MailAccountTemplate(user));
			return true;
		}
		return false;
	}

//	@PostMapping(path = "/login")
//	public Account login(@RequestBody RegistrationUserDto login) {
//		if (login.login != null && login.password != null) {
//			Optional<Account> account = accountService.findByLogin(login.login);
//			if (account.isPresent()) {
//				return getAccountIfPasswordIsValid(login, account);
//			} else {
//				LOGGER.error("Account with login {" + login + "} not found");
//				return new Account("Not exists");
//			}
//		}
//		return new Account("Invalid data");
//	}
//
//	private Account getAccountIfPasswordIsValid(RegistrationUserDto login, Optional<Account> account) {
//		String encodedPassword = encoderService.encodePassword(login.password);
//		if (account.get().getPassword().equals(encodedPassword)) {
//			account.get().setStatus("Success");
//			return account.get();
//		} else {
//			account.get().setStatus("Incorrect password");
//			return account.get();
//		}
//	}
//
//	@PostMapping("/changePass")
//	public Account changePassword(@RequestBody RegistrationUserDto user) {
//		if ((!user.login.equals("") && !user.oldPassword.equals("") && !user.newFirstPassword.equals("")
//				&& !user.newSecondPassword.equals(""))) {
//			Optional<Account> account = accountService.findByLogin(user.login);
//			if (account.isPresent()) {
//				String encodedOldPassword = encoderService.encodePassword(user.oldPassword);
//				String encodedNewPassword = encoderService.encodePassword(user.newFirstPassword);
//				return getUpdatedAccount(user, account, encodedOldPassword, encodedNewPassword);
//			}
//		}
//
//		return new Account("Invalid data");
//	}
//
//	private Account getUpdatedAccount(RegistrationUserDto user, Optional<Account> account, String encodedOldPassword,
//			String encodedNewPassword) {
//		if (account.get().getPassword().equals(encodedOldPassword)) {
//			return updatePasswordAndGetAccount(user, account, encodedNewPassword);
//		} else {
//			account.get().setStatus("Invalid pass");
//			return account.get();
//		}
//	}
//
//	private Account updatePasswordAndGetAccount(RegistrationUserDto user, Optional<Account> account,
//			String encodedNewPassword) {
//		if (user.newFirstPassword.equals(user.newSecondPassword)) {
//			account.get().setPassword(encodedNewPassword);
//			accountService.update(account);
//			account.get().setStatus("Success");
//			return account.get();
//		} else {
//			account.get().setStatus("No match");
//			return account.get();
//		}
//	}
//
//	@PostMapping("/restorePass")
//	public Account rememberPassword(@RequestBody RegistrationUserDto user) {
//		if (!user.login.equals("") && !user.email.equals("")) {
//			Optional<Account> account = accountService.findByLogin(user.login);
//			if (account.isPresent()) {
//				if (account.get().getLogin().equals(user.login)) {
//					user.password = PasswordGenerator.generateRandomPassword(8);
//					String encodedPassword = encoderService.encodePassword(user.password);
//					account.get().setPassword(encodedPassword);
//					accountService.update(account);
//					account.get().setStatus("Success");
//					mailService.sendMail(user, new MailPasswordTemplate(user));
//					return account.get();
//				} else {
//					return new Account("Invalid login");
//				}
//			} else {
//				return new Account("Not exists");
//			}
//		} else
//			return new Account("Invalid data");
//	}
//
//	@GetMapping("/count/all")
//	public Integer countAccounts() {
//		return accountService.countNoGmAccounts();
//	}
//
//	@PostMapping("/sendMess")
//	public Account sendMessage(@RequestBody RegistrationUserDto user) {
//		if ((!user.email.equals("") && !user.message.equals("") && !user.login.equals(""))) {
//			Optional<Account> account = accountService.findByLogin(user.login);
//			if (account.isPresent()) {
//				if (account.get().getLogin().equals(user.login)) {
//					user.login = account.get().getLogin();
//					account.get().setStatus("Success");
//					mailService.sendMail(user, new MailTemplate(user));
//					return account.get();
//				} else
//					return new Account("Invalid login");
//			} else {
//				return new Account("Email not found");
//			}
//		} else
//			return new Account("Invalid data");
//	}

}
