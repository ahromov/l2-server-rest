package ua.cc.lajdev.login.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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

import ua.cc.lajdev.login.dto.UserDto;
import ua.cc.lajdev.login.model.MailSettings;
import ua.cc.lajdev.login.service.MailService;
import ua.cc.lajdev.login.service.impl.mail.MessageTemplate;

@Service
public class MailServiceImpl implements MailService {

	private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailSettings mailSettings;

	public boolean isCorrectEmailAddress(String email) {
		try {
			InternetAddress emailAddr = new InternetAddress(email);

			emailAddr.validate();

			if (isMxRecords(email) == false)
				return false;
		} catch (AddressException ex) {
			logger.error("Incorrect email address");

			return false;
		}

		return true;
	}

	public boolean sendMail(UserDto user, MessageTemplate template) {
		MimeMessage msg = mailSender.createMimeMessage();

		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(msg, true);
			helper.setTo(user.email);
			helper.setReplyTo(mailSettings.getUsername());
			helper.setSubject(template.getSubject());
			helper.setText(template.getBoby(), true);

			mailSender.send(msg);
		} catch (MessagingException e) {
			logger.error(e.getMessage());

			return false;
		} catch (MailException e) {
			logger.error(e.getMessage());

			return false;
		}

		return true;
	}

	private boolean isMxRecords(String email) {
		String domainName = email.substring(email.indexOf("@") + 1);

		InitialDirContext iDirC = null;

		Attributes attributes = null;

		try {
			iDirC = new InitialDirContext();

			attributes = iDirC.getAttributes("dns:/" + domainName, new String[] { "MX" });
		} catch (NamingException e) {
			logger.error("Cannot get MX records");

			return false;
		}

		Attribute attributeMX = attributes.get("MX");

		if (attributeMX == null) {
			return false;
		}

		return true;
	}

}