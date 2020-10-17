package ua.cc.lajdev.login.dto.user;

import ua.cc.lajdev.login.model.Account;

public class UserDto {

	public String login;
	public String password;
	public String passwordSecond;
	public String email;
	public String oldPassword;
	public String newFirstPassword;
	public String newSecondPassword;
	public String message;

	public Account toAccount() {
		return new Account(this.login, this.password, this.email);
	}

}
