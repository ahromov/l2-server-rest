package ua.cc.lajdev.login.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.login.model.Account;
import ua.cc.lajdev.login.model.MailSettings;
import ua.cc.lajdev.login.service.MailService;
import ua.cc.lajdev.login.service.impl.mail.MailPasswordTemplate;
import ua.cc.lajdev.login.service.impl.mail.MailTemplate;
import ua.cc.lajdev.login.service.impl.mail.Template;
import ua.cc.lajdev.site.model.User;

@Service
public class MailServiceImpl implements MailService {

	private static Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

	private final JavaMailSender mailSender;
	private final MailSettings mailSettings;

	@Autowired
	public MailServiceImpl(JavaMailSender mailSender, MailSettings mailSettings) {
		this.mailSender = mailSender;
		this.mailSettings = mailSettings;
	}

	public boolean isCorrectDomainEmailAddress(String email) {
		if (isMxRecords(email))
			return true;
		return false;
	}

	public void sendMail(Account account, Template template) {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
			if (template instanceof MailTemplate) {
				helper.setTo(mailSettings.getUsername());
				helper.setReplyTo(account.getEmail());
			} else {
				helper.setTo(account.getEmail());
			}
			helper.setSubject(template.getSubject());
			helper.setText(template.getBoby(), true);
			mailSender.send(msg);
		} catch (MessagingException | MailException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void sendMail(User account, MailPasswordTemplate template) {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
			helper.setTo(account.getEmail());
			helper.setSubject(template.getSubject());
			helper.setText(template.getBoby(), true);
			mailSender.send(msg);
		} catch (MessagingException | MailException e) {
			LOGGER.error(e.getMessage());
		}
	}

	private boolean isMxRecords(String email) {
		String domainName = email.substring(email.indexOf("@") + 1);
		InitialDirContext dirContext = null;
		Attributes attributes = null;
		try {
			dirContext = new InitialDirContext();
			attributes = dirContext.getAttributes("dns:/" + domainName, new String[] { "MX" });
		} catch (NamingException e) {
			LOGGER.error("Cannot get MX records");
			return false;
		}
		Attribute attributeMX = attributes.get("MX");
		if (attributeMX == null) {
			return false;
		}
		return true;
	}

}
