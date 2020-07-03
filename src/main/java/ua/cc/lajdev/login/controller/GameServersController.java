package ua.cc.lajdev.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.login.model.GameServer;
import ua.cc.lajdev.login.service.GameServerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("ls")
public class GameServersController {

	@Autowired
	GameServerService service;

	@GetMapping("/getServers")
	List<GameServer> getAllRegistered() {
		return service.getAll();
	}

}
