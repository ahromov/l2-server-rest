package ua.cc.lajdev.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.model.Castle;
import ua.cc.lajdev.game.service.CastleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("castles")
public class CastleController {

	@Autowired
	private CastleService castleService;

	@GetMapping("/get/all")
	public List<Castle> getCastles() {
		return castleService.getAll();
	}

}
