package ua.cc.lajdev.common.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect email")
public class IncorrectEmailException extends RuntimeException {

	private static final long serialVersionUID = -2572357449711562294L;

	public IncorrectEmailException() {
		super();
	}

}
