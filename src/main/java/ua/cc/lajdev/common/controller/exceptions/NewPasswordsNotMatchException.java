package ua.cc.lajdev.common.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "New passwords not match")
public class NewPasswordsNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 5811911050818255617L;

	public NewPasswordsNotMatchException() {
		super();
	}

}
