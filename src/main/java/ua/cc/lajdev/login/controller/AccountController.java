package ua.cc.lajdev.login.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.dto.UserDto;
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
					String subject = "Welcome " + user.login + "!";

					String message = "<h2>Your account data:</h2><br><p>Login: " + user.login + "<br>Password: "
							+ user.password + "</p>";

					try {
						Account loginAcc = accountService.findByLogin(user.login);
						loginAcc.setStatus("Login exists");

						return loginAcc;
					} catch (NoSuchElementException e) {
						String encodedPassword = encodePassword(user.password);

						if (isCorrectEmailAddress(user.email)) {
							if (sendMail(user.login, user.email, mailSettings.getUsername(), subject, message)) {
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

			String encodedPassword = encodePassword(login.password);

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

				String encodedOldPassword = encodePassword(user.oldPassword);

				String encodedNewPassword = encodePassword(user.newFirstPassword);

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
					String newPassword = PasswordGenerator.generateRandomPassword(8);

					String encodedPassword = encodePassword(newPassword);

					String subject = "Change password ... " + user.login + "!";

					String messTemplate = "<h1>Password succesful changed!</h1><p>Your new password: " + newPassword;

					if (sendMail(user.login, user.email, mailSettings.getUsername(), subject, messTemplate)) {
						account.setPassword(encodedPassword);
						account = accountService.update(account);
						account.setStatus("Success");

						return account;
					}
				} else {
					return new Account("Invalid login"); // Must return new Account, beacause return correct login
				}
			} else {
				logger.error("Cannot restore password: account with email {" + user.email + "} not found");

				return new Account("Not exists");
			}
		} else
			return new Account("Invalid data");

		return account;
	}

	@GetMapping("/countAll")
	public Long countAccounts() {
		return accountService.countAll();
	}

	@PostMapping("/sendMess")
	public Account sendMessage(@RequestBody UserDto user) {
		Account account = null;

		if ((!user.email.equals("") && !user.message.equals("") && !user.login.equals(""))) {
			account = accountService.findByEmail(user.email);

			if (account != null) {
				if (account.getLogin().equals(user.login)) {
					String subject = "Question from site by " + account.getLogin() + "!";

					if (sendMail(account.getLogin(), mailSettings.getUsername(), user.email, subject, user.message)) {
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

			if (isMxRecords(email) == false)
				return false;
		} catch (AddressException ex) {
			logger.error("Incorrect email address");

			return false;
		}

		return true;
	}

	private boolean isMxRecords(String email) {
		String domainName = email.substring(email.indexOf("@") + 1);

		InitialDirContext iDirC = null;

		Attributes attributes = null;

		try {
			iDirC = new InitialDirContext();

			attributes = iDirC.getAttributes("dns:/" + domainName, new String[] { "MX" });
		} catch (NamingException e) {
			logger.error("Cannot get MX records");

			return false;
		}

		Attribute attributeMX = attributes.get("MX");

		if (attributeMX == null) {
			return false;
		}

		return true;
	}

}
