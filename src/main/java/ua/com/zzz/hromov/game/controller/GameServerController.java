package ua.com.zzz.hromov.game.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.zzz.hromov.game.dto.GameServerDto;
import ua.com.zzz.hromov.game.service.CharService;

@CrossOrigin(origins = "http://hromovl2test.zzz.com.ua", allowedHeaders = "*")
@RestController
public class GameServerController {

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
	    return new GameServerDto();
	}
    }

}
