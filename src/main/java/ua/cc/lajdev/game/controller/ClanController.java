package ua.cc.lajdev.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.model.Clan;
import ua.cc.lajdev.game.service.ClanService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("clans")
public class ClanController {

	private final ClanService clanService;

	@Autowired
	public ClanController(ClanService clanService) {
		this.clanService = clanService;
	}

	@GetMapping("/count/all")
	public Long countAllClans() {
		return clanService.countAll();
	}

	@GetMapping("/count/allAllys")
	public Long countAllAllys() {
		return clanService.countAllAllys();
	}

	@GetMapping("/get/all")
	public List<Clan> getAll() {
		return clanService.getAll();
	}

}
