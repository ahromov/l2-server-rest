package ua.cc.lajdev.game.service;

import java.util.List;

import ua.cc.lajdev.game.model.Clan;

public interface ClanService {

	public Long countAll();

	public List<Clan> getAll();

}
