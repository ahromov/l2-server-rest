package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "heroes")
public class Hero {

	@Id
	@Column
	private Integer charId;

	public Hero() {

	}

	public Integer getCharId() {
		return charId;
	}

	public void setCharId(Integer charId) {
		this.charId = charId;
	}

}
