package hromov.game.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hromov.game.dto.GameServerDto;
import hromov.game.service.CharService;

@CrossOrigin(origins = "http://la2dev.000webhostapp.com", allowedHeaders = "*")
@RestController
public class GameServerController {

    private static Logger logger = LoggerFactory.getLogger(GameServerController.class);
    private static InetSocketAddress address;
    private static Integer timeout;

    static {
	address = new InetSocketAddress("127.0.0.1", 7777);
	timeout = 3000;
    }

    @Autowired
    CharService service;

    @GetMapping("/status")
    public GameServerDto getServerStatus() {
	try (Socket socket = new Socket()) {
	    socket.connect(address, timeout);
	    return new GameServerDto("ON", service.countOnlineChars());
	} catch (IOException e) {
	    logger.error(e.getMessage());
	    return new GameServerDto();
	}
    }

}
