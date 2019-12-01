package ua.cc.lajdev.game.dto;

public class CharDto {

    public String playerName;
    public String playerClass;
    public String playerGender;
    public String playersClan;
    public Integer playersOnlineTime;
    public Integer playersPvpKills;
    public Integer playersPkKills;

    public CharDto(String playerName, String playerClass, String playerGender, String playersClan,
	    Integer playersOnlineTime, Integer playersPvpKills, Integer playersPkKills) {
	this.playerName = playerName;
	this.playerClass = playerClass;
	this.playerGender = playerGender;
	this.playersClan = playersClan;
	this.playersOnlineTime = playersOnlineTime;
	this.playersPvpKills = playersPvpKills;
	this.playersPkKills = playersPkKills;
    }

}
