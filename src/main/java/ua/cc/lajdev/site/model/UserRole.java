package ua.cc.lajdev.site.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "userid")
	private Long userId;

	@Column(name = "role")
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long userRoleId) {
		this.id = userRoleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserid(Long userid) {
		this.userId = userid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
