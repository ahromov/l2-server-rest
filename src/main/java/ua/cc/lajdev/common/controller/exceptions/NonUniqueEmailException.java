package ua.cc.lajdev.common.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Account witn same email exists")
public class NonUniqueEmailException extends RuntimeException {

	private static final long serialVersionUID = -3411007369706054566L;

	public NonUniqueEmailException() {
		super();
	}
}
