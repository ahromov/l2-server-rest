package ua.cc.lajdev.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phpbb_icons")
public class Icon {

	@Id
	@Column(name = "icons_id")
	@JsonIgnore
	private Integer id;

	@Column(name = "icons_url")
	private String url;

	public Icon() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
