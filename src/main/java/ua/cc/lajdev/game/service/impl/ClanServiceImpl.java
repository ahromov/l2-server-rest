package ua.cc.lajdev.game.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Clan;

import ua.cc.lajdev.game.repo.ClanRepository;
import ua.cc.lajdev.game.service.ClanService;

@Service
public class ClanServiceImpl implements ClanService {

	private final ClanRepository repository;

	@Autowired
	public ClanServiceImpl(ClanRepository repository) {
		this.repository = repository;
	}

	@Override
	public Long countAll() {
		return repository.countClans();
	}

	@Override
	public List<Clan> getAll() {
		return repository.findAll();
	}

	@Override
	public Long countAllAllys() {
		return repository.countAllAllys();
	}

}
