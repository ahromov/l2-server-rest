package ua.cc.lajdev.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.repo.ClanRepository;

@Service
public class ClanService {

	@Autowired
	private ClanRepository repository;

	public Long countClans() {
		return repository.countClans();
	}

}
