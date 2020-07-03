package ua.cc.lajdev.game.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@ConfigurationProperties(prefix = "l2jgame.server")
public class ServerSettings {

	@JsonIgnore
	private String ip;

	@JsonIgnore
	private Integer port;

	public ServerSettings() {

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

}
