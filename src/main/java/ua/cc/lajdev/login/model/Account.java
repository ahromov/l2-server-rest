package ua.cc.lajdev.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	@Transient
	private String status;

	@Column(nullable = true)
	@JsonIgnore
	private Integer accessLevel;

	public Account() {

	}

	public Account(String status) {
		this.status = status;
	}

	public Account(String login, String status) {
		this.login = login;
		this.status = status;
	}

	public Account(String login, String password, String email) {
		this.login = login;
		this.password = password;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(Integer accessLevel) {
		this.accessLevel = accessLevel;
	}

}
