package ua.cc.lajdev.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.dto.GoogleResponseDto;
import ua.cc.lajdev.login.dto.GoogleTokenDto;
import ua.cc.lajdev.login.service.CaptchaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("reCaptcha")
public class ReCaptchaController {

	private final CaptchaService recaptchaService;

	@Autowired
	public ReCaptchaController(CaptchaService recaptchaService) {
		this.recaptchaService = recaptchaService;
	}

	@PostMapping("validate")
	public GoogleResponseDto getGoogleResponse(@RequestBody GoogleTokenDto token) {
		return recaptchaService.processResponse(token.response);
	}

}
