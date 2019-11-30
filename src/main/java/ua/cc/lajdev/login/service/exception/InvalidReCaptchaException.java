package hromov.login.service.exception;

public class InvalidReCaptchaException extends Exception {

    private static final long serialVersionUID = 3623191507018607549L;

    public InvalidReCaptchaException(String message) {
	super(message);
    }

}
