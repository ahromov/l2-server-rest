package ua.cc.lajdev.game.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.repo.HeroRepository;
import ua.cc.lajdev.game.service.HeroService;

@Service
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository repository;

	@Override
	public Long countHeroes() {
		return repository.countHeroes();
	}

}
