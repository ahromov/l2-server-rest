package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.user.UserDto;

public class MailPasswordTemplate extends Template {

	public MailPasswordTemplate(UserDto user) {
		this.subject = user.login + ", Password succesfuly changed!";
		this.body = "Your new password: " + user.password;
	}

}
