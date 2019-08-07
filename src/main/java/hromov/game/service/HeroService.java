package hromov.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.repo.HeroRepository;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public Long countHeroes() {
	return heroRepository.countHeroes();
    }

}
