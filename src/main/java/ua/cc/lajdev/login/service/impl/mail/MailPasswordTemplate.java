package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.UserDto;

public class MailPasswordTemplate extends Template {

	public MailPasswordTemplate(UserDto user) {
		this.subject = "Change password ... " + user.login + "!";
		this.body = "<h1>Password succesful changed!</h1><p>Your new password: " + user.password;
	}

}
