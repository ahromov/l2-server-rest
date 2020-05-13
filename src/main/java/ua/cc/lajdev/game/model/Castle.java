package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "castle")
public class Castle {

	@Id
	@Column
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "taxPercent")
	private Integer taxPercent;

	@Column(name = "treasury")
	private Integer treasury;

	@Column(name = "siegeDate")
	private Long siegeDate;

	@OneToOne(mappedBy = "castle")
	private Clan clan;

	public Castle() {

	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getTaxPercent() {
		return taxPercent;
	}

	public Integer getTreasury() {
		return treasury;
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

}
