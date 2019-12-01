package ua.cc.lajdev.game.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.cc.lajdev.game.model.Char;
import ua.cc.lajdev.game.dto.CharDto;
import ua.cc.lajdev.game.service.CharService;
import ua.cc.lajdev.game.service.ClanService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CharacterController {

    @Autowired
    CharService charService;

    @Autowired
    ClanService clanService;

    private static Map<Integer, String> classesIds = new HashMap<>();

    static {
	classesIds.put(1, "Human Warrior");
	classesIds.put(2, "Gladiator");
	classesIds.put(3, "Warlord");
	classesIds.put(4, "Human Knight");
	classesIds.put(5, "Paladin");
	classesIds.put(6, "Dark Avenger");
	classesIds.put(7, "Rogue");
	classesIds.put(8, "Treasure Hunter");
	classesIds.put(9, "Hawkeye");
	classesIds.put(10, "Human Mage");
	classesIds.put(11, "Human Wizard");
	classesIds.put(12, "Sorcerer");
	classesIds.put(13, "Necromancer");
	classesIds.put(14, "Warlock");
	classesIds.put(15, "Cleric");
	classesIds.put(16, "Bishop");
	classesIds.put(17, "Prophet");
	classesIds.put(88, "Duelist");
	classesIds.put(89, "Dread Nought");
	classesIds.put(90, "Phoenix Knight");
	classesIds.put(91, "Hell Knight");
	classesIds.put(92, "Sagittarius");
	classesIds.put(93, "Adventurer");
	classesIds.put(94, "Archmage");
	classesIds.put(95, "Soul Traker");
	classesIds.put(96, "Arcane Lord");
	classesIds.put(97, "Cardinal");
	classesIds.put(98, "Hierophant");
	classesIds.put(18, "Elven Fighter");
	classesIds.put(19, "Elven Knight");
	classesIds.put(20, "Temple Knight");
	classesIds.put(21, "Swordsinger");
	classesIds.put(22, "Elven Scout");
	classesIds.put(23, "Plainswalker");
	classesIds.put(24, "Silver Ranger");
	classesIds.put(25, "Elven Mage");
	classesIds.put(26, "Elven Wizard");
	classesIds.put(27, "Spellsinger");
	classesIds.put(28, "Elemental Summoner");
	classesIds.put(29, "Elven Oracle");
	classesIds.put(30, "Elven Elder");
	classesIds.put(99, "Evas Templar");
	classesIds.put(100, "Sword Muse");
	classesIds.put(101, "Wind Rider");
	classesIds.put(102, "Moonlight Sentinel");
	classesIds.put(103, "Mystic Muse");
	classesIds.put(104, "Elemental Master");
	classesIds.put(105, "Evas Saint");
	classesIds.put(31, "Dark Elven Fighter");
	classesIds.put(32, "Pallus Knight");
	classesIds.put(33, "Shillien Knight");
	classesIds.put(34, "Bladedancer");
	classesIds.put(35, "Assasin");
	classesIds.put(36, "Abyss Walker");
	classesIds.put(37, "Phantom Ranger");
	classesIds.put(38, "Dark Elven Mage");
	classesIds.put(39, "Dark Wizard");
	classesIds.put(40, "Spellhowler");
	classesIds.put(41, "Phantom Summoner");
	classesIds.put(42, "Shillien Oracle");
	classesIds.put(43, "Shillien Elder");
	classesIds.put(106, "Shillien Templar");
	classesIds.put(107, "Spectral Dancer");
	classesIds.put(108, "Ghost Hunter");
	classesIds.put(109, "Ghost Sentinel");
	classesIds.put(110, "Storm Screamer");
	classesIds.put(111, "Spectral Master");
	classesIds.put(112, "Shillien Saint");
	classesIds.put(44, "Orc Fighter");
	classesIds.put(45, "Orc Raider");
	classesIds.put(46, "Destroyer");
	classesIds.put(47, "Monk");
	classesIds.put(48, "Tyrant");
	classesIds.put(49, "Orc Mage");
	classesIds.put(50, "Orc Shaman");
	classesIds.put(51, "Overlord");
	classesIds.put(52, "Warcryer");
	classesIds.put(113, "Titan");
	classesIds.put(114, "Grand Khauatari");
	classesIds.put(115, "Dominator");
	classesIds.put(116, "Doomcryer");
	classesIds.put(53, "Dwarven Fighter");
	classesIds.put(54, "Scavenger");
	classesIds.put(55, "Bounty Hunter");
	classesIds.put(56, "Artisan");
	classesIds.put(57, "Warsmith");
	classesIds.put(117, "Fortune Seeker");
	classesIds.put(118, "Maestro");
	classesIds.put(119, "World Trap");
	classesIds.put(120, "Player Trap");
	classesIds.put(121, "Double Ghost");
	classesIds.put(122, "Siege Attacker");
	classesIds.put(123, "Male Kamael Soldier");
	classesIds.put(124, "Female Kamael Soldier");
	classesIds.put(125, "Trooper");
	classesIds.put(126, "Warder");
	classesIds.put(127, "Berserker");
	classesIds.put(128, "Male Soul Breaker");
	classesIds.put(129, "Female Soul Breaker");
	classesIds.put(130, "Arbalester");
	classesIds.put(131, "Doombringer");
	classesIds.put(132, "Male Soul Hound");
	classesIds.put(133, "Female Soul Hound");
	classesIds.put(134, "Trickster");
	classesIds.put(135, "Inspector");
	classesIds.put(136, "Judicator");
    }

    @GetMapping("/getTopTen")
    public LinkedList<CharDto> getTop10chars() {
	List<Char> chars = charService.getTop10Chars();

	Collections.sort(chars, Collections.reverseOrder(new Comparator<Char>() {
	    @Override
	    public int compare(Char o1, Char o2) {
		return o1.getOnlineTime() - o2.getOnlineTime();
	    }
	}));

	LinkedList<CharDto> charsDto = new LinkedList<>();

	for (int i = 0; i < chars.size(); i++) {
	    Char ch = chars.get(i);

	    charsDto.add(
		    new CharDto(ch.getName(), classesIds.get(ch.getClassId()), ch.getGenderId() == 0 ? "Муж" : "Жен",
			    ch.getClanId() == 0 ? "None" : clanService.getById(ch.getClanId()).getName(),
			    (ch.getOnlineTime() / 60) / 60, ch.getPvPKills(), ch.getPkKills()));
	}

	return charsDto;
    }

}
