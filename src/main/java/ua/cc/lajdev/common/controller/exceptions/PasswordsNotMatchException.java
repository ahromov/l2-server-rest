package ua.cc.lajdev.common.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Passwords not match")
public class PasswordsNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 7504813047782040974L;

	public PasswordsNotMatchException() {
		super();
	}

}
