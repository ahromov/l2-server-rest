package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.dto.user.UserDto;
import ua.cc.lajdev.login.service.impl.mail.Template;

public interface MailService {

	boolean isCorrectEmailAddress(String email);

	void sendMail(UserDto user, Template template);

}
