package ua.cc.lajdev.login.service.impl;

import java.net.URI;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import ua.cc.lajdev.login.controller.ReCaptchaController;
import ua.cc.lajdev.login.dto.GoogleResponseDto;
import ua.cc.lajdev.login.model.CaptchaSettings;
import ua.cc.lajdev.login.service.CaptchaService;
import ua.cc.lajdev.login.service.exception.InvalidReCaptchaException;

@Service
public class CaptchaServiceImpl implements CaptchaService {

	private static Logger LOGGER = LoggerFactory.getLogger(ReCaptchaController.class);
	private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

	private final CaptchaSettings captchaSettings;
	private final RestOperations restTemplate;

	@Autowired
	public CaptchaServiceImpl(CaptchaSettings captchaSettings, RestOperations restTemplate) {
		this.captchaSettings = captchaSettings;
		this.restTemplate = restTemplate;
	}

	@Override
	public GoogleResponseDto processResponse(String response) {
		try {
			responseSanityCheck(response);
		} catch (InvalidReCaptchaException e) {
			LOGGER.error(e.getMessage());
		}

		URI verifyUri = URI
				.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s",
						captchaSettings.getSecret(), response));

		GoogleResponseDto googleResponse = restTemplate.getForObject(verifyUri, GoogleResponseDto.class);

		try {
			isResponseSuccesss(googleResponse);
		} catch (InvalidReCaptchaException e) {
			LOGGER.error(e.getMessage());
		}

		return googleResponse;
	}

	private void responseSanityCheck(String response) throws InvalidReCaptchaException {
		if (!StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches()) {
			throw new InvalidReCaptchaException("Response contains invalid characters");
		}
	}

	private void isResponseSuccesss(GoogleResponseDto googleResponse) throws InvalidReCaptchaException {
		if (!googleResponse.isSuccess()) {
			throw new InvalidReCaptchaException("reCaptcha was not successfully validated");
		}
	}

}
