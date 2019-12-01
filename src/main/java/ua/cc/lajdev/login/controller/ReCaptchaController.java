package ua.cc.lajdev.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.dto.GoogleResponseDto;
import ua.cc.lajdev.login.service.CaptchaService;
import ua.cc.lajdev.login.service.exception.InvalidReCaptchaException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ReCaptchaController {

    @Autowired
    CaptchaService recaptchaService;

    @PostMapping("captcha/validate")
    public GoogleResponseDto getGoogleResponse(@RequestParam("response") String response) {
	try {
	    return recaptchaService.processResponse(response);
	} catch (InvalidReCaptchaException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
