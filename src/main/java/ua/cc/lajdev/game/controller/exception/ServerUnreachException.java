package ua.cc.lajdev.game.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "Server unreach")
public class ServerUnreachException extends RuntimeException {

	private static final long serialVersionUID = 1188234929051726655L;

	public ServerUnreachException() {
		super();
	}

}
