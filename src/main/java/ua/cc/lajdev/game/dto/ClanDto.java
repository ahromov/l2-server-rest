package ua.cc.lajdev.game.dto;

public class ClanDto {

    public String name;
    public Integer level;
    public String leader;
    public String hasCastle;
    public Integer reputation;
    public Integer middLevel;
    public String alyName;

    public ClanDto(String name, Integer level, String leader, String hasCastle, Integer reputation, Integer middLevel,
	    String alyName) {
	this.name = name;
	this.level = level;
	this.leader = leader;
	this.hasCastle = hasCastle;
	this.reputation = reputation;
	this.middLevel = middLevel;
	this.alyName = alyName;
    }

}
