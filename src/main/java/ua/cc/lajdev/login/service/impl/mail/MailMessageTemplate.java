package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.UserDto;

public class MailMessageTemplate extends MessageTemplate {

	public MailMessageTemplate(UserDto user) {
		this.subject = "Question from site by " + user.login + "!";
		this.boby = user.message;
	}

}
