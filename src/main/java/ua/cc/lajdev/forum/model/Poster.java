package ua.cc.lajdev.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phpbb_users")
public class Poster {

	@Id
	@Column(name = "user_id")
	@JsonIgnore
	private Integer id;

	@Column
	private String username;

	public Poster() {

	}

	public Integer getUserId() {
		return id;
	}

	public void setUserId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
