package hromov.login.dto;

public class AccountDto {

    public String message;
    public boolean isMailSended = false;

    public AccountDto(String message) {
	this.message = message;
    }

    public AccountDto(String message, boolean isMailSended) {
	this.message = message;
	this.isMailSended = isMailSended;
    }

}
