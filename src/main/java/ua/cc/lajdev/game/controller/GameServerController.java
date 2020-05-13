package ua.cc.lajdev.game.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.model.GameServer;
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
			server.setOnlineCounter(characterService.getOnlineNoneGmChars());

			return server;
		} catch (IOException e) {
			server.setStatus("OFF");
			server.setOnlineCounter(0);

			logger.error(e.getMessage());

			return server;
		}
	}

}
