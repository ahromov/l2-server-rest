package ua.cc.lajdev.common.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ua.cc.lajdev.login.model.Account;

public class RegistrationDto extends RestorePasswordDto {

	@Size(min = 8)
	@NotBlank
	public String password;

	@Size(min = 8)
	@NotBlank
	public String repeatedPassword;

	public Account toAccount(String encodedPassword) {
		return new Account(this.login, encodedPassword, this.email);
	}

}
