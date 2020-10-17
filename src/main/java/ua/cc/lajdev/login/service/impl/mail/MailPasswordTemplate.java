package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.RegistrationUserDto;

public class MailPasswordTemplate extends Template {

	public MailPasswordTemplate(RegistrationUserDto user) {
		this.subject = "Change password ... " + user.login + "!";
		this.body = "<h1>Password succesful changed!</h1><p>Your new password: " + user.password;
	}

}
