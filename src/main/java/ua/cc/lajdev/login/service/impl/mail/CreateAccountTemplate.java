package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.UserDto;

public class CreateAccountTemplate extends MessageTemplate {

	public CreateAccountTemplate(UserDto user) {
		this.subject = "Welcome " + user.login + "!";
		this.boby = "<h2>Your account data:</h2><br><p>Login: " + user.login + "<br>Password: " + user.password
				+ "</p>";
	}

}
