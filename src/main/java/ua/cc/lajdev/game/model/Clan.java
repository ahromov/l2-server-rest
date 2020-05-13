package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "clan_data")
public class Clan {

	@Id
	@Column(name = "clan_id")
	private Integer id;

	@Column(name = "clan_name")
	private String name;

	@Column(name = "clan_level")
	private Integer level;

	@Column(name = "leader_id")
	private Integer leaderId;

	@OneToOne
	@JoinColumn(name = "hasCastle", referencedColumnName = "id")
	private Castle castle;

	@OneToOne(mappedBy = "clan")
	private Fort fort;

	@Column(name = "reputation_score")
	private Integer reputation;

	@Column(name = "ally_name")
	private String alyName;

	public Clan() {
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getLeaderId() {
		return leaderId;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

	public Castle getCastle() {
		return castle;
	}

	public Fort getFort() {
		return fort;
	}

	public void setFort(Fort fort) {
		this.fort = fort;
	}

	public Integer getReputation() {
		return reputation;
	}

	public String getAlyName() {
		return alyName;
	}

	public void setAlyName(String alyName) {
		this.alyName = alyName;
	}

}
