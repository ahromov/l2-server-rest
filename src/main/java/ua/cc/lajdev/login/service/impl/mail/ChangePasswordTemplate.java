package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.UserDto;

public class ChangePasswordTemplate extends MessageTemplate {

	public ChangePasswordTemplate(UserDto user) {
		this.subject = "Change password ... " + user.login + "!";
		this.boby = "<h1>Password succesful changed!</h1><p>Your new password: " + user.password;
	}

}
