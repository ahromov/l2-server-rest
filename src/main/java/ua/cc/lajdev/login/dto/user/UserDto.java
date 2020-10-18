package ua.cc.lajdev.login.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import ua.cc.lajdev.login.model.Account;

public class UserDto {

	@NotBlank
	public String login;

	@Email
	public String email;

	public String password;
	public String passwordSecond;
	public String oldPassword;
	public String newFirstPassword;
	public String newSecondPassword;
	public String message;

	public Account toAccount() {
		return new Account(this.login, this.password, this.email);
	}

}
