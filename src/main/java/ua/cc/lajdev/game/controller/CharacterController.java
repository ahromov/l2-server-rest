package ua.cc.lajdev.game.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.dto.CharsDto;
import ua.cc.lajdev.game.model.Char;
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

	@GetMapping("/get/{id}")
	public Char getChars(@PathVariable Integer id) {
		return charService.getById(id);
	}

	@GetMapping("/count/byType")
	public CharsDto countChars() {
		return new CharsDto(charService.countAllChars(), charService.countNoblessChars(), heroService.countHeroes(),
				charService.countGmChars());
	}

	@GetMapping("/get/all")
	public List<Char> getChars() {
		return charService.getAll();
	}

	@GetMapping("/get/top10")
	public List<Char> getTop10chars() {
		List<Char> chars = charService.getTop10Chars();

		Collections.sort(chars, Collections.reverseOrder(new Comparator<Char>() {
			@Override
			public int compare(Char o1, Char o2) {
				return o1.getOnlineTime() - o2.getOnlineTime();
			}
		}));

		return chars;
	}

}
