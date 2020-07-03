package ua.cc.lajdev.game.dto;

public class StatusDto {

	private String status;
	private Integer onlineCounter = 0;

	public StatusDto() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOnlineCounter() {
		return onlineCounter;
	}

	public void setOnlineCounter(Integer onlineCounter) {
		this.onlineCounter = onlineCounter;
	}

}
