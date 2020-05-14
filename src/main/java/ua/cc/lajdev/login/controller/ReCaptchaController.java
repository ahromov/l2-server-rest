package ua.cc.lajdev.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.dto.GoogleResponseDto;
import ua.cc.lajdev.login.service.CaptchaService;
import ua.cc.lajdev.login.service.exception.InvalidReCaptchaException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("reCaptcha")
public class ReCaptchaController {

	private static Logger logger = LoggerFactory.getLogger(ReCaptchaController.class);

	@Autowired
	CaptchaService recaptchaService;

	@PostMapping("validate")
	public GoogleResponseDto getGoogleResponse(@RequestParam("response") String response) {
		try {
			return recaptchaService.processResponse(response);
		} catch (InvalidReCaptchaException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

}
