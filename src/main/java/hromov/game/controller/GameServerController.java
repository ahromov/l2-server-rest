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

import hromov.game.dto.CharsDto;
import hromov.game.dto.GameServerDto;
import hromov.game.service.CharService;
import hromov.game.service.ClanService;
import hromov.game.service.HeroService;

@CrossOrigin(origins = "https://93.170.116.143", allowedHeaders = "*")
@RestController
public class GameServerController {

    private static Logger logger = LoggerFactory.getLogger(GameServerController.class);
    private static InetSocketAddress address;
    private static Integer timeout;

    static {
	address = new InetSocketAddress("93.170.116.143", 7777);
	timeout = 3000;
    }

    @Autowired
    private CharService characterService;

    @Autowired
    private HeroService heroService;

    @Autowired
    private ClanService clanService;

    @GetMapping("/status")
    public GameServerDto getServerStatus() {
	try (Socket socket = new Socket()) {
	    socket.connect(address, timeout);
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
