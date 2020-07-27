package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.dto.UserDto;
import ua.cc.lajdev.login.service.impl.mail.MessageTemplate;

public interface MailService {

	boolean isCorrectEmailAddress(String email);

	void sendMail(UserDto user, MessageTemplate template);

}
