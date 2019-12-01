package ua.cc.lajdev.game.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "l2jgame.server")
public class GameServer {

    private String ip;
    private Integer port;

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
