package ua.cc.lajdev.game.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.controller.exception.ServerUnreachException;
import ua.cc.lajdev.game.service.CharService;
import ua.cc.lajdev.game.service.RatesService;
import ua.cc.lajdev.game.service.ServerStatusService;
import ua.cc.lajdev.game.service.impl.ServerStatusServiceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("gs")
public class GameServerController {

	private static Logger LOGGER = LoggerFactory.getLogger(ServerStatusServiceImpl.class);

	private final ServerStatusService statusService;
	private final CharService characterService;
	private final RatesService rateService;

	@Autowired
	public GameServerController(ServerStatusService statusService, CharService characterService,
			RatesService rateService) {
		this.statusService = statusService;
		this.characterService = characterService;
		this.rateService = rateService;
	}

	@GetMapping("/status")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer checkServerStatus() {
		try {
			statusService.checkStatus();
			return characterService.getOnlineNoGmPlayers();
		} catch (IOException e) {
			LOGGER.error("Game server unreach ...");
			throw new ServerUnreachException();
		}
	}

	@GetMapping("/get/rates")
	public Map<String, String> getServerRates() {
		return rateService.getRates();
	}

}
