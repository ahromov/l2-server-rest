package ua.cc.lajdev.game.controller;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.dto.CastleDto;
import ua.cc.lajdev.game.model.Castle;
import ua.cc.lajdev.game.model.Clan;
import ua.cc.lajdev.game.service.CastleService;
import ua.cc.lajdev.game.service.ClanService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CastleController {

    @Autowired
    private CastleService castleService;

    @Autowired
    private ClanService clanService;

    @GetMapping("/getCastles")
    public List<CastleDto> getCastles() {
	List<Castle> castles = castleService.getAll();
	LinkedList<CastleDto> castlesDto = new LinkedList<>();

	for (Castle castle : castles) {
	    Clan clan = clanService.getByCastleId(castle.getId());

	    long siegeDate = castle.getSiegeDate();

	    castlesDto.add(new CastleDto(castle.getId(), castle.getName(), castle.getTaxPercent(), castle.getTreasury(),
		    siegeDate == 0 || new Timestamp(siegeDate).before(new Date()) ? "Незарегистрирована"
			    : new Timestamp(castle.getSiegeDate()).toLocalDateTime()
				    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
		    clan != null ? clan.getName() : "Нет"));

	}

	return castlesDto;
    }

}
