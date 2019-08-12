package hromov.game.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hromov.game.dto.ClanDto;
import hromov.game.model.Char;
import hromov.game.model.Clan;
import hromov.game.service.CastleService;
import hromov.game.service.CharService;
import hromov.game.service.ClanService;

@CrossOrigin(origins = "https://93.170.116.143", allowedHeaders = "*")
@RestController
public class ClanController {

    @Autowired
    private ClanService clanService;

    @Autowired
    private CharService charService;

    @Autowired
    private CastleService castleService;

    @GetMapping("/getClans")
    public List<ClanDto> getClans() {
	List<Clan> clans = clanService.getClans();
	List<ClanDto> clansDto = new ArrayList<>();

	for (Clan clan : clans) {
	    List<Char> clanMembers = charService.getAllByClanId(clan.getClanId());

	    int memberslevelSum = 0;

	    for (Char member : clanMembers) {
		memberslevelSum += member.getLevel();
	    }

	    String castleName = "";
	    if (castleService.getById(clan.getCastleId()).getId() != 0)
		castleName = castleService.getById(clan.getCastleId()).getName();
	    else
		castleName = "Нет";

	    String alyName = clan.getAlyName();

	    clansDto.add(new ClanDto(clan.getName(), clan.getLevel(), charService.getById(clan.getLeaderId()).getName(),
		    castleName, clan.getReputation(), memberslevelSum / clanMembers.size(),
		    alyName == null ? "Нет" : alyName));
	}

	return clansDto;
    }

}
