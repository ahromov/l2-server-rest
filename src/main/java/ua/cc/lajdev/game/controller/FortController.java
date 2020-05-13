package ua.cc.lajdev.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.model.Fort;
import ua.cc.lajdev.game.service.FortService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FortController {

	@Autowired
	private FortService fortService;

//	@Autowired
//	private ClanService clanService;

//	@GetMapping("/getForts")
//	public List<FortDto> getForts() {
//		List<Fort> forts = fortService.getAll();
//		List<FortDto> fortsDto = new LinkedList<>();
//
//		for (Fort fort : forts) {
//			Clan clan = null;
//			int owner = fort.getOwner();
//			if (owner != 0)
//				clan = clanService.getById(owner);
//
//			long siegeDate = fort.getSiegeDate();
//
//			fortsDto.add(new FortDto(fort.getId(), fort.getName(),
//					siegeDate == 0 || new Timestamp(siegeDate).before(new Date()) ? "Незарегистрирована"
//							: new Timestamp(siegeDate).toLocalDateTime()
//									.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
//					clan != null ? clan.getName() : "Нет"));
//		}
//
//		return fortsDto;
//	}

	@GetMapping("/getForts")
	public List<Fort> getForts() {
		return fortService.getAll();
	}

}
