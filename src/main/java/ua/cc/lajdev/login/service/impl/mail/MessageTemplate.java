package ua.cc.lajdev.login.service.impl.mail;

public abstract class MessageTemplate {

	protected String subject;
	protected String boby;

	public String getSubject() {
		return subject;
	}

	public String getBoby() {
		return boby;
	}

}
