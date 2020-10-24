package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.common.dto.user.MessageDto;

public class MailTemplate extends Template {

	public MailTemplate(MessageDto user) {
		this.subject = "Mail from " + user.login + "!";
		this.body = user.message;
	}

}
