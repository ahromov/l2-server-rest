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

	@Autowired
	private ClanService clanService;

	@GetMapping("/count/all")
	public Long countAllClans() {
		return clanService.countClans();
	}

	@GetMapping("/get/all")
	public List<Clan> getAll() {
		return clanService.getAll();
	}

}
