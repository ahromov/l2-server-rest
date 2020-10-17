package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.dto.user.UserDto;

public class MailPasswordTemplate extends Template {

	public MailPasswordTemplate(UserDto user) {
		this.subject = "Change password ... " + user.login + "!";
		this.body = "Password succesful changed!\n\tYour new password: " + user.password;
	}

}
