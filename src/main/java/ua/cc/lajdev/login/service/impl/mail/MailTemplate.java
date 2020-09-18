package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.UserDto;

public class MailTemplate extends Template {

	public MailTemplate(UserDto user) {
		this.subject = "Question from site by " + user.login + "!";
		this.body = user.message;
	}

}
