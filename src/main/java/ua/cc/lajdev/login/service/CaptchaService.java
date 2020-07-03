package ua.cc.lajdev.login.service;

import ua.cc.lajdev.login.dto.GoogleResponseDto;

public interface CaptchaService {

	GoogleResponseDto processResponse(String response);

}
