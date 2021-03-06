package ua.cc.lajdev.common.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid datas")
public class InvalidDatasException extends RuntimeException {

	private static final long serialVersionUID = -6824587445004465265L;

	public InvalidDatasException() {
		super();
	}

}
