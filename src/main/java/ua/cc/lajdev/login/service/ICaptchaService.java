package hromov.login.service;

import hromov.login.dto.GoogleResponseDto;
import hromov.login.service.exception.InvalidReCaptchaException;

public interface ICaptchaService {

    GoogleResponseDto processResponse(String response) throws InvalidReCaptchaException;

}
