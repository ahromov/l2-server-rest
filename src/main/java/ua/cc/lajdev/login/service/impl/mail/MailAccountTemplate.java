package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.UserDto;

public class MailAccountTemplate extends Template {

	public MailAccountTemplate(UserDto user) {
		this.subject = "Welcome " + user.login + "!";
		this.body = "<h2>Your account data:</h2><br><p>Login: " + user.login + "<br>Password: " + user.password
				+ "</p>";
	}

}
