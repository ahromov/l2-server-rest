package ua.cc.lajdev.login.service.impl.mail;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.site.model.User;

public class MailPasswordTemplate extends Template {

	public MailPasswordTemplate(Account account) {
		this.subject = account.getLogin() + ", Password succesfuly changed!";
		this.body = "Your new password: " + account.getPassword();
	}

	public MailPasswordTemplate(User account) {
		this.subject = account.getUserName() + ", Password succesfuly changed!";
		this.body = "Your new password: " + account.getPassword();
	}

}
