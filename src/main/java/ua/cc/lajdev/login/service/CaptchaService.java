package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.dto.GoogleResponseDto;
import ua.cc.lajdev.login.service.exception.InvalidReCaptchaException;

public interface CaptchaService {

    GoogleResponseDto processResponse(String response) throws InvalidReCaptchaException;

}
