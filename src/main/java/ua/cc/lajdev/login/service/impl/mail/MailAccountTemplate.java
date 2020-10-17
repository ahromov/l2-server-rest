package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.RegistrationUserDto;

public class MailAccountTemplate extends Template {

	public MailAccountTemplate(RegistrationUserDto user) {
		this.subject = "Welcome " + user.login + "!";
		this.body = "Your account data:\n\tLogin: " + user.login + "\n\tPassword: " + user.password;
	}

}
