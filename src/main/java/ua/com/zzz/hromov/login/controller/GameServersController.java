package ua.com.zzz.hromov.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.zzz.hromov.login.dto.GameServersDto;
import ua.com.zzz.hromov.login.model.GameServer;
import ua.com.zzz.hromov.login.service.GameServerService;

@CrossOrigin(origins = "http://la2dev.000webhostapp.com", allowedHeaders = "*")
@RestController
public class GameServersController {

    private static String[] serversNames = { "Bartz", "Sieghardt", "Kain", "Lionna", "Erica", "Gustin", "Devianne",
	    "Hindemith", "Teon (EURO)", "Franz (EURO)", "Luna (EURO)", "Sayha", "Aria", "Phoenix", "Chronos",
	    "Naia (EURO)", "Elhwynna", "Ellikia", "Shikken", "Scryde", "Frikios", "Ophylia", "Shakdun", "Tarziph",
	    "Aria", "Esenn", "Elcardia", "Yiana", "Seresin", "Tarkai", "Khadia", "Roien", "Kallint (Non-PvP)", "Baium",
	    "Kamael", "Beleth", "Anakim", "Lilith", "Thifiel", "Lithra", "Lockirin", "Kakai", "Cadmus", "Athebaldt",
	    "Blackbird", "Ramsheart", "Esthus", "Vasper", "Lancer", "Ashton", "Waytrel", "Waltner", "Tahnford",
	    "Hunter", "Dewell", "Rodemaye", "Ken Rauhel", "Ken Abigail", "Ken Orwen", "Van Holter", "Desperion",
	    "Einhovant", "Shunaiman", "Faris", "Tor", "Carneiar", "Dwyllios", "Baium", "Hallate", "Zaken", "Core" };

    @Autowired
    GameServerService service;

    @GetMapping("/getServers")
    GameServersDto getAllRegistered() {
	List<GameServer> servers = service.getAll();

	GameServersDto serversDto = new GameServersDto(new ArrayList<String>());

	for (GameServer s : servers) {
	    serversDto.names.add(serversNames[s.getId() - 1]);
	}

	return serversDto;
    }

}
