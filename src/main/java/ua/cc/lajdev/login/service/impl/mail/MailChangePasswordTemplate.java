package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.UserDto;

public class MailChangePasswordTemplate extends Template {

	public MailChangePasswordTemplate(UserDto user) {
		this.subject = "Change password ... " + user.login + "!";
		this.body = "<h1>Password succesful changed!</h1><p>Your new password: " + user.password;
	}

}
