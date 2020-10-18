package ua.cc.lajdev.login.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ua.cc.lajdev.login.model.Account;

public class UserDto {

	@NotBlank
	public String login;

	@Email
	public String email;

	@Size(min = 8)
	public String password;

	@Size(min = 20)
	public String message;

	public String repeatedPassword;
	public String oldPassword;
	public String newFirstPassword;
	public String newSecondPassword;

	public Account toAccount() {
		return new Account(this.login, this.password, this.email);
	}

}
