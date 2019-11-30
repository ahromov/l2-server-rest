package hromov.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "clan_data")
public class Clan {

    @Id
    @Column(name = "clan_id")
    private Integer clanId;

    @Column(name = "clan_name")
    private String name;

    @Column(name = "clan_level")
    private Integer level;

    @Column(name = "leader_id")
    private Integer leaderId;

    @Column(name = "hasCastle")
    private Integer hasCastle;

    @Column(name = "reputation_score")
    private Integer reputation;

    @Column(name = "ally_name")
    private String alyName;

    public Clan() {
    }

    public Integer getClanId() {
	return clanId;
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

    public Integer getCastleId() {
	return hasCastle;
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
