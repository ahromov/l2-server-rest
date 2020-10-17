package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.RegistrationUserDto;

public class MailTemplate extends Template {

	public MailTemplate(RegistrationUserDto user) {
		this.subject = "Question from site by " + user.login + "!";
	}

}
