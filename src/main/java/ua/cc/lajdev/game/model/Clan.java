package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "clan_data")
public class Clan {

	@Id
	@Column(name = "clan_id")
	@JsonIgnore
	private Integer id;

	@Column(name = "clan_name")
	private String name;

	@Column(name = "clan_level")
	private Integer level;

	@OneToOne
	@JoinColumn(name = "leader_id", referencedColumnName = "charId")
	private Char leader;

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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Char getLeader() {
		return leader;
	}

	public void setLeader(Char leader) {
		this.leader = leader;
	}

	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
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

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public String getAlyName() {
		return alyName;
	}

	public void setAlyName(String alyName) {
		this.alyName = alyName;
	}

}
