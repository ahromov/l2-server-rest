package ua.cc.lajdev.game.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "characters")
@Scope("prototype")
public class Char {

	@Id
	@JsonIgnore
	private Integer charId;

	@JsonIgnore
	@Column(name = "account_name")
	private String accountName;

	@Column(name = "char_name")
	private String charName;

	@Column(name = "level")
	private Integer level;

	@OneToOne
	@JoinColumn(name = "clanid", referencedColumnName = "clan_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Clan clan;

	@Column
	private Integer online;

	@JsonIgnore
	@Column(name = "classid")
	private Integer classId;

	@Transient
	private String className;

	@JsonIgnore
	@Column(name = "sex")
	private Integer genderId;

	@Transient
	private String gender;

	@Column(name = "onlinetime")
	private Integer onlineTime;

	@Column(name = "pvpkills")
	private Integer pvpKills;

	@Column(name = "pkkills")
	private Integer pkKills;

	private static Map<Integer, String> classes;
	private static Map<Integer, String> genders;

	static {
		classes = new HashMap<>();
		classes.put(1, "Human Warrior");
		classes.put(2, "Gladiator");
		classes.put(3, "Warlord");
		classes.put(4, "Human Knight");
		classes.put(5, "Paladin");
		classes.put(6, "Dark Avenger");
		classes.put(7, "Rogue");
		classes.put(8, "Treasure Hunter");
		classes.put(9, "Hawkeye");
		classes.put(10, "Human Mage");
		classes.put(11, "Human Wizard");
		classes.put(12, "Sorcerer");
		classes.put(13, "Necromancer");
		classes.put(14, "Warlock");
		classes.put(15, "Cleric");
		classes.put(16, "Bishop");
		classes.put(17, "Prophet");
		classes.put(88, "Duelist");
		classes.put(89, "Dread Nought");
		classes.put(90, "Phoenix Knight");
		classes.put(91, "Hell Knight");
		classes.put(92, "Sagittarius");
		classes.put(93, "Adventurer");
		classes.put(94, "Archmage");
		classes.put(95, "Soul Traker");
		classes.put(96, "Arcane Lord");
		classes.put(97, "Cardinal");
		classes.put(98, "Hierophant");
		classes.put(18, "Elven Fighter");
		classes.put(19, "Elven Knight");
		classes.put(20, "Temple Knight");
		classes.put(21, "Swordsinger");
		classes.put(22, "Elven Scout");
		classes.put(23, "Plainswalker");
		classes.put(24, "Silver Ranger");
		classes.put(25, "Elven Mage");
		classes.put(26, "Elven Wizard");
		classes.put(27, "Spellsinger");
		classes.put(28, "Elemental Summoner");
		classes.put(29, "Elven Oracle");
		classes.put(30, "Elven Elder");
		classes.put(99, "Evas Templar");
		classes.put(100, "Sword Muse");
		classes.put(101, "Wind Rider");
		classes.put(102, "Moonlight Sentinel");
		classes.put(103, "Mystic Muse");
		classes.put(104, "Elemental Master");
		classes.put(105, "Evas Saint");
		classes.put(31, "Dark Elven Fighter");
		classes.put(32, "Pallus Knight");
		classes.put(33, "Shillien Knight");
		classes.put(34, "Bladedancer");
		classes.put(35, "Assasin");
		classes.put(36, "Abyss Walker");
		classes.put(37, "Phantom Ranger");
		classes.put(38, "Dark Elven Mage");
		classes.put(39, "Dark Wizard");
		classes.put(40, "Spellhowler");
		classes.put(41, "Phantom Summoner");
		classes.put(42, "Shillien Oracle");
		classes.put(43, "Shillien Elder");
		classes.put(106, "Shillien Templar");
		classes.put(107, "Spectral Dancer");
		classes.put(108, "Ghost Hunter");
		classes.put(109, "Ghost Sentinel");
		classes.put(110, "Storm Screamer");
		classes.put(111, "Spectral Master");
		classes.put(112, "Shillien Saint");
		classes.put(44, "Orc Fighter");
		classes.put(45, "Orc Raider");
		classes.put(46, "Destroyer");
		classes.put(47, "Monk");
		classes.put(48, "Tyrant");
		classes.put(49, "Orc Mage");
		classes.put(50, "Orc Shaman");
		classes.put(51, "Overlord");
		classes.put(52, "Warcryer");
		classes.put(113, "Titan");
		classes.put(114, "Grand Khauatari");
		classes.put(115, "Dominator");
		classes.put(116, "Doomcryer");
		classes.put(53, "Dwarven Fighter");
		classes.put(54, "Scavenger");
		classes.put(55, "Bounty Hunter");
		classes.put(56, "Artisan");
		classes.put(57, "Warsmith");
		classes.put(117, "Fortune Seeker");
		classes.put(118, "Maestro");
		classes.put(119, "World Trap");
		classes.put(120, "Player Trap");
		classes.put(121, "Double Ghost");
		classes.put(122, "Siege Attacker");
		classes.put(123, "Male Kamael Soldier");
		classes.put(124, "Female Kamael Soldier");
		classes.put(125, "Trooper");
		classes.put(126, "Warder");
		classes.put(127, "Berserker");
		classes.put(128, "Male Soul Breaker");
		classes.put(129, "Female Soul Breaker");
		classes.put(130, "Arbalester");
		classes.put(131, "Doombringer");
		classes.put(132, "Male Soul Hound");
		classes.put(133, "Female Soul Hound");
		classes.put(134, "Trickster");
		classes.put(135, "Inspector");
		classes.put(136, "Judicator");

		genders = new HashMap<>();
		genders.put(0, "Male");
		genders.put(1, "Female");
	}

	public Char() {

	}

	public Integer getCharId() {
		return charId;
	}

	public void setCharId(Integer charId) {
		this.charId = charId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		this.charName = charName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getPvpKills() {
		return pvpKills;
	}

	public void setPvpKills(Integer pvpKills) {
		this.pvpKills = pvpKills;
	}

	public Integer getPkKills() {
		return pkKills;
	}

	public void setPkKills(Integer pkKills) {
		this.pkKills = pkKills;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((charId == null) ? 0 : charId.hashCode());
		result = prime * result + ((charName == null) ? 0 : charName.hashCode());
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((online == null) ? 0 : online.hashCode());
		result = prime * result + ((onlineTime == null) ? 0 : onlineTime.hashCode());
		result = prime * result + ((pkKills == null) ? 0 : pkKills.hashCode());
		result = prime * result + ((pvpKills == null) ? 0 : pvpKills.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Char other = (Char) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (charId == null) {
			if (other.charId != null)
				return false;
		} else if (!charId.equals(other.charId))
			return false;
		if (charName == null) {
			if (other.charName != null)
				return false;
		} else if (!charName.equals(other.charName))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (online == null) {
			if (other.online != null)
				return false;
		} else if (!online.equals(other.online))
			return false;
		if (onlineTime == null) {
			if (other.onlineTime != null)
				return false;
		} else if (!onlineTime.equals(other.onlineTime))
			return false;
		if (pkKills == null) {
			if (other.pkKills != null)
				return false;
		} else if (!pkKills.equals(other.pkKills))
			return false;
		if (pvpKills == null) {
			if (other.pvpKills != null)
				return false;
		} else if (!pvpKills.equals(other.pvpKills))
			return false;
		return true;
	}

	@PostLoad
	public void initFields() {
		this.className = classes.get(this.classId);
		this.gender = genders.get(this.genderId);
	}

}
