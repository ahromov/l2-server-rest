package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "fort")
public class Fort {

	@Id
	@Column
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "siegeDate")
	private Long siegeDate;

	@OneToOne
	@JoinColumn(name = "owner", referencedColumnName = "clan_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	private Clan clan;

	@Transient
	private String clanName = "None";

	public Fort() {

	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getSiegeDate() {
		return siegeDate;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	@PostLoad
	void initFields() {
		if (clan != null)
			this.clanName = clan.getName();
	}

}
