package ua.cc.lajdev.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accounts")
@Scope("prototype")
public class Account {

	@Id
	@Column
	private String login;

	@Column
	@JsonIgnore
	private String password;

	@Column
	@JsonIgnore
	private String email;

	public Account() {

	}

	public Account(String login, String status) {
		this.login = login;
	}

	public Account(String login, String password, String email) {
		this(login, password);
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
