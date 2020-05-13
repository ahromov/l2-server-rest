package ua.cc.lajdev.game.dto;

public class CastleDto {

	public Integer id;
	public String name;
	public Integer taxPercent;
	public Integer treasury;
	public String siegeDate;
	public String leaderName;

	public CastleDto(Integer id, String name, Integer taxPercent, Integer treasury, String string, String leaderName) {
		this.id = id;
		this.name = name;
		this.taxPercent = taxPercent;
		this.treasury = treasury;
		this.siegeDate = string;
		this.leaderName = leaderName;
	}

}
