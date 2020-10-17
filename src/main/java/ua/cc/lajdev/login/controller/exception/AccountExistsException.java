package ua.cc.lajdev.login.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Account exists")
public class AccountExistsException extends RuntimeException {

	private static final long serialVersionUID = -2572357449711562294L;

	public AccountExistsException() {
		super();
	}

}
