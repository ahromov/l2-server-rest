package ua.cc.lajdev.game.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.model.GameServer;
import ua.cc.lajdev.game.model.Rates;
import ua.cc.lajdev.game.service.CharService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("gs")
public class GameServerController {

	private static Logger logger = LoggerFactory.getLogger(GameServerController.class);

	@Autowired
	private GameServer server;

	@Autowired
	private CharService characterService;

	@GetMapping("/get/status")
	public GameServer getServerStatus() {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(server.getIp(), server.getPort()), 3000);

			server.setStatus("ON");
			server.setOnlineCounter(characterService.getOnlineNoGm());

			return server;
		} catch (IOException e) {
			server.setStatus("OFF");
			server.setOnlineCounter(0);

			logger.error(e.getMessage());

			return server;
		}
	}

	@GetMapping("/get/rates")
	public Map<String, String> getServerRates() {
		InputStream isr = null;

		try {
			isr = new FileInputStream(server.getRatesConfigFile());
		} catch (FileNotFoundException e) {
			logger.error("Rates properties file not found");
		}

		Properties props = new Properties();
		try {
			props.load(isr);
		} catch (IOException e) {
			logger.error("Cannot load properties from stream");
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
