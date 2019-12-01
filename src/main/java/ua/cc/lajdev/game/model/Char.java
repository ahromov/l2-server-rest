package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "characters")
public class Char {

    @Id
    @Column
    private Integer charId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "char_name")
    private String charName;

    @Column(name = "level")
    private Integer level;

    @Column
    private Integer clanid;

    @Column
    private Integer online;

    @Column(name = "classid")
    private Integer classId;

    @Column(name = "sex")
    private Integer genderId;

    @Column(name = "onlinetime")
    private Integer onlineTime;

    @Column(name = "pvpkills")
    private Integer pvpKills;

    @Column(name = "pkkills")
    private Integer pkKills;

    public Char() {
    }

    public Integer getOnline() {
	return online;
    }

    public Integer getCharId() {
	return charId;
    }

    public String getAccountName() {
	return accountName;
    }

    public String getName() {
	return charName;
    }

    public Integer getLevel() {
	return level;
    }

    public Integer getClanId() {
	return clanid;
    }

    public Integer getClassId() {
	return classId;
    }

    public Integer getGenderId() {
	return genderId;
    }

    public Integer getOnlineTime() {
	return onlineTime;
    }

    public Integer getPvPKills() {
	return pvpKills;
    }

    public Integer getPkKills() {
	return pkKills;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((onlineTime == null) ? 0 : onlineTime.hashCode());
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
	if (onlineTime == null) {
	    if (other.onlineTime != null)
		return false;
	} else if (!onlineTime.equals(other.onlineTime))
	    return false;
	return true;
    }

}
