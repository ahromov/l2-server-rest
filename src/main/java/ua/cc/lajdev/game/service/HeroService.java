package ua.cc.lajdev.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.repo.HeroRepository;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    public Long countHeroes() {
	return repository.countHeroes();
    }

}
