package ua.com.zzz.hromov.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.zzz.hromov.game.dto.CharDto;
import ua.com.zzz.hromov.game.service.impl.CharService;

@CrossOrigin(origins = "http://hromovl2test.zzz.com.ua", allowedHeaders = "*")
@RestController
public class CharController {

    @Autowired
    CharService service;

    @GetMapping("/online")
    public CharDto getOnlineChars() {
	return new CharDto(service.countOnlineChars());
    }

}
