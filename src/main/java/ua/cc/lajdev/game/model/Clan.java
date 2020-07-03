package ua.cc.lajdev.game.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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

	@OneToOne
	@JoinColumn(name = "hasCastle", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	private Castle castle;

	@Transient
	private String castleName = "None";

	@OneToOne(mappedBy = "clan")
	@JsonIgnore
	private Fort fort;

	@Transient
	private String fortName = "None";

	@Column(name = "reputation_score")
	private Integer reputation;

	@Column(name = "ally_name")
	private String alyName;

	@Transient
	private Integer midCharsLevel;

	@OneToMany(mappedBy = "clan", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<PlayersChar> chars;

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

	public Integer getMidCharsLevel() {
		return midCharsLevel;
	}

	public void setMidCharsLevel(Integer midCharsLevel) {
		this.midCharsLevel = midCharsLevel;
	}

	public List<PlayersChar> getChars() {
		return chars;
	}

	public void setChars(List<PlayersChar> chars) {
		this.chars = chars;
	}

	@PostLoad
	void initFields() {
		this.leaderName = leader.getCharName();

		if (fort != null)
			this.castleName = castle.getName();

		if (castle != null)
			this.fortName = fort.getName();

		this.midCharsLevel = calculateMidCharsLevel();
	}

	private Integer calculateMidCharsLevel() {
		int midLevel = 0;

		for (PlayersChar c : this.chars) {
			if (c.getLevel() != null)
				midLevel += c.getLevel();
		}

		return midLevel = midLevel / this.chars.size();
	}

}
