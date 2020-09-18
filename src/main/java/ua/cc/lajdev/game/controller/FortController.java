package ua.cc.lajdev.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.model.Fort;
import ua.cc.lajdev.game.service.FortService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("forts")
public class FortController {

	private final FortService fortService;

	@Autowired
	public FortController(FortService fortService) {
		this.fortService = fortService;
	}

	@GetMapping("/get/all")
	public List<Fort> getForts() {
		return fortService.getAll();
	}

}
