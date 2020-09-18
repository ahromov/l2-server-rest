package ua.cc.lajdev.game.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clan_data")
@Scope("prototype")
public class Clan {

	@Id
	@Column(name = "clan_id")
	@JsonIgnore
	private Long id;

	@Column(name = "clan_name")
	private String name;

	@Column(name = "clan_level")
	private Integer level;

	@OneToOne
	@JoinColumn(name = "leader_id", referencedColumnName = "charId")
	@JsonIgnore
	private PlayersChar leader;

	@Transient
	private String leaderName;

	@Transient
	private String fortName = "None";

	@Column(name = "reputation_score")
	private Integer reputation;

	@Column(name = "ally_name")
	private String alyName;

	@Column(name = "ally_id")
	@JsonIgnore
	private Integer allyId;

	@Transient
	private Integer midCharsLevel;

	@OneToMany(mappedBy = "clan")
	@JsonIgnore
	private Set<PlayersChar> chars = new HashSet<PlayersChar>();

	@OneToOne
	@JoinColumn(name = "hasCastle", referencedColumnName = "id")
	@JsonIgnore
	private Castle castle;

	@Transient
	private String castleName = "None";

	@OneToOne(mappedBy = "clan")
	@JsonIgnore
	private Fort fort;

	public Clan() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public PlayersChar getLeader() {
		return leader;
	}

	public void setLeader(PlayersChar leader) {
		this.leader = leader;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

	public String getCastleName() {
		return castleName;
	}

	public void setCastleName(String castleName) {
		this.castleName = castleName;
	}

	public Fort getFort() {
		return fort;
	}

	public void setFort(Fort fort) {
		this.fort = fort;
	}

	public String getFortName() {
		return fortName;
	}

	public void setFortName(String fortName) {
		this.fortName = fortName;
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

	public Integer getAllyId() {
		return allyId;
	}

	public void setAllyId(Integer allyId) {
		this.allyId = allyId;
	}

	public Integer getMidCharsLevel() {
		return midCharsLevel;
	}

	public void setMidCharsLevel(Integer midCharsLevel) {
		this.midCharsLevel = midCharsLevel;
	}

	public Set<PlayersChar> getChars() {
		return chars;
	}

	@SuppressWarnings("unused")
	private void setChars(Set<PlayersChar> chars) {
		this.chars = chars;
	}

	@PostLoad
	void initFields() {
		this.leaderName = leader.getCharName();

		if (fort != null)
			this.fortName = fort.getName();

		if (castle != null)
			this.castleName = castle.getName();

		if (this.chars.size() > 0)
			this.midCharsLevel = calculateMidCharsLevel();
	}

	private Integer calculateMidCharsLevel() {
		return this.chars.stream().filter((x) -> x.getLevel() != null).mapToInt((x) -> x.getLevel())
				.reduce(Math::addExact).getAsInt() / this.chars.size();
	}

}
