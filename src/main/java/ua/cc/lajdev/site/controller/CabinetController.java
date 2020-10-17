package ua.cc.lajdev.site.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.common.controller.exceptions.AccountNotFoundException;
import ua.cc.lajdev.common.controller.exceptions.InvalidDatasException;
import ua.cc.lajdev.common.controller.exceptions.PasswordsNotMatchException;
import ua.cc.lajdev.site.model.User;
import ua.cc.lajdev.site.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("cabinet")
public class CabinetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CabinetController.class);

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CabinetController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/password/change")
	@ResponseStatus(code = HttpStatus.OK)
	public void changePassword(@RequestParam("username") String username,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("secondNewPassword") String secondNewPassword) {
		if (!username.equals("") && !newPassword.equals("") && !secondNewPassword.equals("")) {
			User user = userService.findByUserName(username);
			if (user != null) {
				if (newPassword.equals(secondNewPassword)) {
					user.setPassword(passwordEncoder.encode(newPassword));
					userService.update(user);
					LOGGER.warn("Password changed: " + user);
				} else
					throw new PasswordsNotMatchException();
			} else
				throw new AccountNotFoundException();
		} else
			throw new InvalidDatasException();
	}

}
