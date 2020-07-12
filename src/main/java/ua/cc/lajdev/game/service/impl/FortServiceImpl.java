package ua.cc.lajdev.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Fort;
import ua.cc.lajdev.game.repo.FortRepository;
import ua.cc.lajdev.game.service.FortService;

@Service
public class FortServiceImpl implements FortService {

	private final FortRepository repository;

	@Autowired
	public FortServiceImpl(FortRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Fort> getAll() {
		return repository.findAll();
	}

}
