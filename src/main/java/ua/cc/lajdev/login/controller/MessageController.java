package hromov.login.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hromov.login.dto.AccountDto;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sMess")
    public AccountDto registration(@RequestParam("name") String name, @RequestParam("email") String email,
	    @RequestParam("message") String message, @RequestParam("answer") String answer) {
	if ((!name.equals("") && !email.equals("") && !message.equals("") && !answer.equals(""))
		|| (name != null && email != null && message != null && answer != null)) {

	    if (sendMessage(name, email, message))
		return new AccountDto("Success", true);

	}

	return new AccountDto("Invalid data");
    }

    private boolean sendMessage(String name, String email, String message) {
	MimeMessage msg = javaMailSender.createMimeMessage();

	MimeMessageHelper helper;
	try {
	    helper = new MimeMessageHelper(msg, true);
	    helper.setTo("hromov.la2dev@gmail.com");
	    helper.setReplyTo(email);
	    helper.setSubject("Вопрос с сайта от " + name + "!");
	    helper.setText(message, true);
	} catch (MessagingException e) {
	    logger.error(e.getMessage());
	    return false;
	}

	javaMailSender.send(msg);

	return true;
    }

}
