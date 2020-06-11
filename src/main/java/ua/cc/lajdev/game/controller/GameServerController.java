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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.dto.RatesConfigDto;
import ua.cc.lajdev.game.model.Rates;
import ua.cc.lajdev.game.model.Status;
import ua.cc.lajdev.game.service.CharService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("gs")
public class GameServerController {

	private static Logger logger = LoggerFactory.getLogger(GameServerController.class);

	@Autowired
	private Status serverStatus;

	@Autowired
	private CharService characterService;

	@Autowired
	private RatesConfigDto config;

	private StatusChecker mc = null;

	private class StatusChecker extends Thread {
		public void run() {
			while (true) {
				try {
					checkStatus(serverStatus);

					sleep(3000);
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	@GetMapping("/get/status")
	public Status getServerStatus() {
		checkStatus(serverStatus);

		return serverStatus;
	}

	private void checkStatus(Status status) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(serverStatus.getIp(), serverStatus.getPort()), 3000);

			serverStatus.setStatus("ON");
			serverStatus.setOnlineCounter(characterService.getOnlineNoGm());
		} catch (IOException e) {
			serverStatus.setStatus("OFF");
			logger.error(e.getMessage());
		}
	}

	@PostConstruct
	private void start() {
		mc = new StatusChecker();
		mc.start();
	}

	@MessageMapping("/ping")
	@SendTo("/topic/greetings")
	public Status greeting() throws Exception {
		return serverStatus;
	}

	@PreDestroy
	void stop() {
		mc.interrupt();
	}

	@GetMapping("/get/rates")
	public Map<String, String> getServerRates() {
		InputStream isr = null;

		try {
			isr = new FileInputStream(config.getRatesConfigFile());
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
