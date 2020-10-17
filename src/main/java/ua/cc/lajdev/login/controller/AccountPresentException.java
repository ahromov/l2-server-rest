package ua.cc.lajdev.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Account exists")
public class AccountPresentException extends RuntimeException {

	private static final long serialVersionUID = -2572357449711562294L;

	public AccountPresentException() {
		super();
	}

}
