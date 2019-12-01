package ua.cc.lajdev.login.service;

import java.net.URI;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import ua.cc.lajdev.login.dto.GoogleResponseDto;
import ua.cc.lajdev.login.model.CaptchaSettings;
import ua.cc.lajdev.login.service.exception.InvalidReCaptchaException;

@Service
public class CaptchaService implements ICaptchaService {

    @Autowired
    private CaptchaSettings captchaSettings;

    @Autowired
    private RestOperations restTemplate;

    private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Override
    public GoogleResponseDto processResponse(String response) throws InvalidReCaptchaException {
	if (!responseSanityCheck(response)) {
	    throw new InvalidReCaptchaException("Response contains invalid characters");
	}

	URI verifyUri = URI
		.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s",
			captchaSettings.getSecret(), response));

	GoogleResponseDto googleResponse = restTemplate.getForObject(verifyUri, GoogleResponseDto.class);

	if (!googleResponse.isSuccess()) {
	    throw new InvalidReCaptchaException("reCaptcha was not successfully validated");
	}

	return googleResponse;
    }

    private boolean responseSanityCheck(String response) {
	return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

}
