package hromov.game.dto;

public class FortDto {

    public Integer id;
    public String name;
    public String siegeDate;
    public String owner;

    public FortDto(Integer id, String name, String string, String owner) {
	this.id = id;
	this.name = name;
	this.siegeDate = string;
	this.owner = owner;
    }

}
