package ua.cc.lajdev.common.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Account not exists")
public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9206412161370700340L;

	public AccountNotFoundException() {
		super();
	}

}
