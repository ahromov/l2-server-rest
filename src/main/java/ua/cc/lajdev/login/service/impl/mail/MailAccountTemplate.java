package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.user.UserDto;

public class MailAccountTemplate extends Template {

	public MailAccountTemplate(UserDto user) {
		this.subject = "Welcome " + user.login + "!";
		this.body = "Your account data:\n\tLogin: " + user.login + "\n\tPassword: " + user.password;
	}

}
