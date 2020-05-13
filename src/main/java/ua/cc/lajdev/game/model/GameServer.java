package ua.cc.lajdev.game.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@ConfigurationProperties(prefix = "l2jgame.server")
public class GameServer {

	@JsonIgnore
	private String ip;

	@JsonIgnore
	private Integer port;

	private String status;
	private Integer onlineCounter;

	public GameServer() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
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
