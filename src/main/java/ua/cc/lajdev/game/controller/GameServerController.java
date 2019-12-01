package ua.cc.lajdev.game.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.dto.CharsDto;
import ua.cc.lajdev.game.dto.GameServerDto;
import ua.cc.lajdev.game.model.GameServer;
import ua.cc.lajdev.game.service.CharService;
import ua.cc.lajdev.game.service.ClanService;
import ua.cc.lajdev.game.service.HeroService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GameServerController {

    private static Logger logger = LoggerFactory.getLogger(GameServerController.class);

    @Autowired
    GameServer server;

    @Autowired
    private CharService characterService;

    @Autowired
    private HeroService heroService;

    @Autowired
    private ClanService clanService;

    @GetMapping("/status")
    public GameServerDto getServerStatus() {
	try (Socket socket = new Socket()) {
	    socket.connect(new InetSocketAddress(server.getIp(), server.getPort()), 3000);
	    return new GameServerDto("ON", characterService.countOnlineChars());
	} catch (IOException e) {
	    logger.error(e.getMessage());
	    return new GameServerDto();
	}
    }

    @GetMapping("/countChars")
    public CharsDto countChars() {
	return new CharsDto(characterService.countAllChars(), characterService.countNoblessChars(),
		heroService.countHeroes(), characterService.countGmChars(), clanService.countClans());
    }

}
