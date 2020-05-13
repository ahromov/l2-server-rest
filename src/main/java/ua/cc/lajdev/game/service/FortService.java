package ua.cc.lajdev.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Fort;
import ua.cc.lajdev.game.repo.FortRepository;

@Service
public class FortService {

	@Autowired
	private FortRepository repository;

	public List<Fort> getAll() {
		return repository.findAll();
	}

}
