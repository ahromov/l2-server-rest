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

	@Size(min = 8)
	public String repeatedPassword;

	@Size(min = 20)
	public String message;

	@Size(min = 8)
	public String oldPassword;

	@Size(min = 8)
	public String newPassword;

	@Size(min = 8)
	public String newRepeatedPassword;

	public Account toAccount(String encodedPassword) {
		return new Account(this.login, encodedPassword, this.email);
	}

}
