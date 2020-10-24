package ua.cc.lajdev.site.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.common.controller.exceptions.AccountNotFoundException;
import ua.cc.lajdev.common.controller.exceptions.IncorrectEmailException;
import ua.cc.lajdev.common.controller.exceptions.IncorrectPasswordException;
import ua.cc.lajdev.common.controller.exceptions.NewPasswordsNotMatchException;
import ua.cc.lajdev.common.controller.exceptions.PasswordsNotMatchException;
import ua.cc.lajdev.common.dto.user.ChangePasswordDto;
import ua.cc.lajdev.common.dto.user.LoginDto;
import ua.cc.lajdev.common.dto.user.RegistrationDto;
import ua.cc.lajdev.common.dto.user.RestorePasswordDto;
import ua.cc.lajdev.common.dto.user.UserDto;
import ua.cc.lajdev.login.service.MailService;
import ua.cc.lajdev.login.service.impl.mail.MailPasswordTemplate;
import ua.cc.lajdev.site.model.User;
import ua.cc.lajdev.site.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("cabinet")
public class CabinetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CabinetController.class);

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final MailService mailService;

	@Autowired
	public CabinetController(UserService userService, PasswordEncoder passwordEncoder, MailService mailService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.mailService = mailService;
	}

	@PostMapping("/changePass")
	@ResponseStatus(code = HttpStatus.OK)
	public void changePassword(@Valid @RequestBody ChangePasswordDto user) {
		try {
			User account = userService.findByUserName(user);
			validate(user, account);
			account.setPassword(user.newPassword);
			mailService.sendMail(account, new MailPasswordTemplate(account));
			account.setPassword(passwordEncoder.encode(user.newPassword));
			userService.update(account);
			user.password = user.newPassword;
			LOGGER.warn("Password changed: " + account);
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException();
		}
	}

	private <T extends UserDto> void validate(T user, User account) {
		if (user instanceof ChangePasswordDto
				&& !passwordEncoder.matches(((ChangePasswordDto) user).password, account.getPassword()))
			throw new IncorrectPasswordException();
		if (user instanceof ChangePasswordDto
				&& !((ChangePasswordDto) user).newPassword.equals(((ChangePasswordDto) user).newRepeatedPassword))
			throw new NewPasswordsNotMatchException();
	}

}
