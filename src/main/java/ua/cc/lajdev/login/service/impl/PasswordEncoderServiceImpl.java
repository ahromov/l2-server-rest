package ua.cc.lajdev.login.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.login.service.PasswordEncoderService;

@Service
public class PasswordEncoderServiceImpl implements PasswordEncoderService {
	
	private static Logger logger = LoggerFactory.getLogger(PasswordEncoderServiceImpl.class);

	public String encodePassword(String password) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}

		String encodedPassword = null;

		try {
			encodedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}

		return encodedPassword;
	}

}
