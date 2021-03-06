package ua.cc.lajdev.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Castle;
import ua.cc.lajdev.game.repo.CastleRepository;
import ua.cc.lajdev.game.service.CastleService;

@Service
public class CastleServiceImpl implements CastleService {

	private final CastleRepository repository;

	@Autowired
	public CastleServiceImpl(CastleRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Castle> getAll() {
		return repository.findAll();
	}

}
