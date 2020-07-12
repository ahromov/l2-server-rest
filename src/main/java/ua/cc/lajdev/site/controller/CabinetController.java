package ua.cc.lajdev.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.site.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("cabinet")
public class CabinetController {

	private final UserService userService;

	@Autowired
	public CabinetController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/password/change")
	public String changePassword(@RequestParam("username") String username,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("secondNewPassword") String secondNewPassword) {
		if (newPassword.contentEquals(secondNewPassword)) {
			if (userService.changePassword(username, newPassword))
				return "Success";
		}

		return "Passwords no match";
	}

}
