package ua.cc.lajdev.game.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Clan;

import ua.cc.lajdev.game.repo.ClanRepository;
import ua.cc.lajdev.game.service.ClanService;

@Service
public class ClanServiceImpl implements ClanService {

	@Autowired
	private ClanRepository repository;

	@Override
	public Long countAll() {
		return repository.countClans();
	}

	@Override
	public List<Clan> getAll() {
		return repository.findAll();
	}

}
