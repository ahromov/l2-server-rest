package ua.cc.lajdev.login.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.model.MailSettings;
import ua.cc.lajdev.login.service.AccountService;
import ua.cc.lajdev.login.utils.PasswordGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("accounts")
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MailSettings mailSettings;

	@PostMapping("/create")
	public Account registration(@RequestParam("login") String login, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("passwordSecond") String passwordSecond) {
		Account account = null;

		if ((!login.equals("") && !email.equals("") && !password.equals("") && !passwordSecond.equals(""))) {
			account = accountService.findByEmail(email);

			if (password.equals(passwordSecond)) {
				if (account != null) {
					account.setStatus("Email exists");

					return account;
				} else {
					String subject = "Welcome " + login + "!";

					String message = "<h2>Your account data:</h2><br><p>Login: " + login + "<br>Password: " + password
							+ "</p>";

					try {
						Account loginAcc = accountService.findByLogin(login);
						loginAcc.setStatus("Login exists");

						return loginAcc;
					} catch (NoSuchElementException e) {
						String encodedPassword = encodePassword(password);

						if (isCorrectEmailAddress(email)) {
							if (sendMail(login, email, mailSettings.getUsername(), subject, message)) {
								account = accountService.create(new Account(login, encodedPassword, email));
								account.setStatus("Success");

								logger.info("New account {" + email + "} created");
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

	@PostMapping("/login")
	public Account login(@RequestParam("login") String login, @RequestParam("password") String password) {
		Account account = null;

		try {
			account = accountService.findByLogin(login);

			String encodedPassword = encodePassword(password);

			if (account.getPassword().equals(encodedPassword)) {
				account = new Account(login, "Success");

				return account;
			} else {
				account = new Account("Incorrect password");

				return account;
			}
		} catch (NoSuchElementException e) {
			logger.error("Account with login {" + login + "} not found");

			account = new Account("Not exists");
		}

		return account;
	}

	@PostMapping("/changePass")
	public Account changePassword(@RequestParam("login") String login, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newFirstPassword") String newFirstPassword,
			@RequestParam("newSecondPassword") String newSecondPassword) {
		Account account = null;

		if ((!login.equals("") && !oldPassword.equals("") && !newFirstPassword.equals("")
				&& !newSecondPassword.equals(""))) {
			try {
				account = accountService.findByLogin(login);

				String encodedOldPassword = encodePassword(oldPassword);

				String encodedNewPassword = encodePassword(newFirstPassword);

				if (account.getPassword().equals(encodedOldPassword)) {
					if (newFirstPassword.equals(newSecondPassword)) {
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
				logger.error("Cannot update password: account width login {" + login + "} not found");
			}
		}

		return account;
	}

	@PostMapping("/restorePass")
	public Account rememberPassword(@RequestParam("login") String login, @RequestParam("email") String email) {
		Account account = null;

		if (!login.equals("") && !email.equals("")) {
			account = accountService.findByEmail(email);

			if (account != null) {
				if (account.getLogin().equals(login)) {
					String newPassword = PasswordGenerator.generateRandomPassword(8);

					String encodedPassword = encodePassword(newPassword);

					String subject = "Change password ... " + login + "!";

					String messTemplate = "<h1>Password succesful changed!</h1><p>Your new password: " + newPassword;

					if (sendMail(login, email, mailSettings.getUsername(), subject, messTemplate)) {
						account.setPassword(encodedPassword);
						account = accountService.update(account);
						account.setStatus("Success");

						return account;
					}
				} else {
					return new Account("Invalid login"); // Must return new Account, beacause return correct login
				}
			} else {
				logger.error("Cannot restore password: account with email {" + email + "} not found");

				return new Account("Not exists");
			}
		} else
			return new Account("Invalid data");

		return account;
	}

	@GetMapping("/countAll")
	public Long countAccounts() {
		return accountService.countAccounts();
	}

	@PostMapping("/sendMess")
	public Account sendMessage(@RequestParam("login") String login, @RequestParam("email") String email,
			@RequestParam("message") String message) {
		Account account = null;

		if ((!email.equals("") && !message.equals("") && !login.equals(""))) {
			account = accountService.findByEmail(email);

			if (account != null) {
				if (account.getLogin().equals(login)) {
					String subject = "Question from site by " + account.getLogin() + "!";

					if (sendMail(account.getLogin(), mailSettings.getUsername(), email, subject, message)) {
						account.setStatus("Success");

						return account;
					}
				} else
					return new Account("Invalid login");
			} else {
				logger.error("Cannot send message: account with email {" + email + "} not found");

				return new Account("Not found");
			}
		} else
			return new Account("Invalid data");

		return account;
	}

	private String encodePassword(String password) {
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

		return encodedPassword;
	}

	private boolean sendMail(String login, String email, String replyMail, String subject, String message) {
		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(msg, true);
			helper.setTo(email);
			helper.setReplyTo(replyMail);
			helper.setSubject(subject);
			helper.setText(message, true);

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			logger.error(e.getMessage());

			return false;
		} catch (MailException e) {
			logger.error(e.getMessage());

			return false;
		}

		return true;
	}

	private boolean isCorrectEmailAddress(String email) {
		try {
			InternetAddress emailAddr = new InternetAddress(email);

			emailAddr.validate();

			try {
				checkDomain(email);
			} catch (UnknownHostException e) {
				logger.error("Unknow mailhost address");

				return false;
			}
		} catch (AddressException ex) {
			logger.error("Incorrect email address");

			return false;
		}

		return true;
	}

	private boolean checkDomain(String email) throws UnknownHostException {
		String mailDomain = email.substring(email.indexOf("@") + 1);

		InetAddress.getByName(mailDomain);

		return true;
	}

}
