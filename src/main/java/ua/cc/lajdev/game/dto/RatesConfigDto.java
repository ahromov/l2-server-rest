package ua.cc.lajdev.game.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "l2jgame.server")
public class RatesConfigDto {

	private String ratesConfigFile;

	public RatesConfigDto() {

	}

	public String getRatesConfigFile() {
		return ratesConfigFile;
	}

	public void setRatesConfigFile(String configFile) {
		this.ratesConfigFile = configFile;
	}

}
