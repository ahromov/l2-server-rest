package ua.cc.lajdev.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.dto.CharsDto;
import ua.cc.lajdev.game.model.PlayersChar;
import ua.cc.lajdev.game.service.CharService;
import ua.cc.lajdev.game.service.ClanService;
import ua.cc.lajdev.game.service.HeroService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("characters")
public class CharacterController {

	@Autowired
	CharService charService;

	@Autowired
	ClanService clanService;

	@Autowired
	private HeroService heroService;

	@GetMapping("/count/byType")
	public CharsDto countChars() {
		return new CharsDto(charService.countAll(), charService.countNobless(), heroService.countHeroes(),
				charService.countGms());
	}

	@GetMapping("/get/top10")
	public List<PlayersChar> getTop10chars() {
		return charService.getTop10();
	}

}
