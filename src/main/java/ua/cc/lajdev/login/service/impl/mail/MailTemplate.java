package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.user.UserDto;

public class MailTemplate extends Template {

	public MailTemplate(UserDto user) {
		this.subject = "Mail from " + user.login + "!";
		this.body = user.message;
	}

}
