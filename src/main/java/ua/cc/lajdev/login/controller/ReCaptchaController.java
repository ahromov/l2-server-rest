package hromov.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hromov.login.dto.GoogleResponseDto;
import hromov.login.service.CaptchaService;
import hromov.login.service.exception.InvalidReCaptchaException;

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
