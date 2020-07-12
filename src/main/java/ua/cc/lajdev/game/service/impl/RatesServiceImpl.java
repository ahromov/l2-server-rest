package ua.cc.lajdev.game.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.dto.RatesConfigDto;
import ua.cc.lajdev.game.model.Rates;
import ua.cc.lajdev.game.service.RatesService;

@Service
public class RatesServiceImpl implements RatesService {

	private static Logger LOGGER = LoggerFactory.getLogger(RatesServiceImpl.class);

	private final RatesConfigDto config;

	@Autowired
	public RatesServiceImpl(RatesConfigDto config) {
		this.config = config;
	}

	public Map<String, String> getRates() {
		InputStream isr = null;

		try {
			isr = new FileInputStream(config.getRatesConfigFile());
		} catch (FileNotFoundException e) {
			LOGGER.error("Rates properties file not found");
		}

		Properties props = new Properties();
		try {
			props.load(isr);
		} catch (IOException e) {
			LOGGER.error("Cannot load properties from stream");
		}

		Map<String, String> rates = new HashMap<>();

		for (Rates propertyName : Rates.values()) {
			if (propertyName.name().contentEquals("DropAmountMultiplierByItemId")) {
				rates.put(propertyName.getPropertyName(), props.getProperty(propertyName.name()).substring(3));
			} else
				rates.put(propertyName.getPropertyName(), props.getProperty(propertyName.name()));
		}

		return rates;
	}

}
