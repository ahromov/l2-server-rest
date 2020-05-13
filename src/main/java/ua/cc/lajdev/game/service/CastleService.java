package ua.cc.lajdev.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Castle;
import ua.cc.lajdev.game.repo.CastleRepository;

@Service
public class CastleService {

	@Autowired
	private CastleRepository repository;

	public Castle getById(Integer id) {
		return repository.findById(id).get();
	}

	public List<Castle> getAll() {
		return repository.findAll();
	}

}
