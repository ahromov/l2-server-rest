package ua.com.zzz.hromov.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "accounts")
public class Account {

    @Id
    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    public Account() {
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
