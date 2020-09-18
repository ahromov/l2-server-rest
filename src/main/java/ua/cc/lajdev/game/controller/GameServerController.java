package ua.cc.lajdev.game.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.dto.StatusDto;
import ua.cc.lajdev.game.service.CharService;
import ua.cc.lajdev.game.service.RatesService;
import ua.cc.lajdev.game.service.ServerStatusService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("gs")
public class GameServerController {

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

	@GetMapping("/get/status")
	public StatusDto getServerStatus() {
		StatusDto status = new StatusDto();
		if (statusService.checkStatus()) {
			status.setStatus("ON");
			status.setOnlineCounter(characterService.getOnlineNoGm());
			return status;
		}
		status.setStatus("OFF");
		return status;
	}

	@GetMapping("/get/rates")
	public Map<String, String> getServerRates() {
		return rateService.getRates();
	}

}
