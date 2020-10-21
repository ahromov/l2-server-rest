package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.service.impl.mail.Template;

public interface MailService {

	boolean isCorrectDomainEmailAddress(String email);

	void sendMail(Account account, Template template);

}
