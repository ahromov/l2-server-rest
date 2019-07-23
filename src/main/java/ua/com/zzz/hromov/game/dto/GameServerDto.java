package ua.com.zzz.hromov.game.dto;

public class GameServerDto {

    public String status = "OFF";
    public Integer onlineCounter = 0;

    public GameServerDto() {
    }

    public GameServerDto(String status, Integer onlineCounter) {
	this.status = status;
	this.onlineCounter = onlineCounter;
    }

}
