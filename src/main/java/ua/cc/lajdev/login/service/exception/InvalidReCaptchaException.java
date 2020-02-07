package ua.cc.lajdev.login.service.exception;

public class InvalidReCaptchaException extends Exception {

	private static final long serialVersionUID = -6492977133216516126L;

	public InvalidReCaptchaException(String message) {
		super(message);
	}

}
