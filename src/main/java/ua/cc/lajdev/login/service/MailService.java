package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.dto.RegistrationUserDto;
import ua.cc.lajdev.login.service.impl.mail.Template;

public interface MailService {

	boolean isCorrectEmailAddress(String email);

	void sendMail(RegistrationUserDto user, Template template);

}
