package ua.cc.lajdev.login.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Account not exists")
public class AccountNotExistsException extends RuntimeException {

	private static final long serialVersionUID = -9206412161370700340L;

	public AccountNotExistsException() {
		super();
	}

}
