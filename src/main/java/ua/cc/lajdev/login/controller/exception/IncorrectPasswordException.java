package ua.cc.lajdev.login.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect password")
public class IncorrectPasswordException extends RuntimeException {

	private static final long serialVersionUID = 6752639155353739636L;

}
