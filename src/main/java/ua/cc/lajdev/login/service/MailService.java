package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.service.impl.mail.MailPasswordTemplate;
import ua.cc.lajdev.login.service.impl.mail.Template;
import ua.cc.lajdev.site.model.User;

public interface MailService {

	boolean isCorrectDomainEmailAddress(String email);

	void sendMail(Account account, Template template);

	void sendMail(User account, MailPasswordTemplate template);

}
