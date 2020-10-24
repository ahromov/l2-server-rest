package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.common.dto.user.RegistrationDto;

public class MailAccountTemplate extends Template {

	public MailAccountTemplate(RegistrationDto user) {
		this.subject = "Welcome " + user.login + "!";
		this.body = "Your account data:\n\tLogin: " + user.login + "\n\tPassword: " + user.password;
	}

}
