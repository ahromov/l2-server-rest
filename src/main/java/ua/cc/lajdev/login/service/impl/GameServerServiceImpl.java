package ua.cc.lajdev.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.login.model.GameServer;
import ua.cc.lajdev.login.repo.GameServerRepository;
import ua.cc.lajdev.login.service.GameServerService;

@Service
public class GameServerServiceImpl implements GameServerService {

	private final GameServerRepository repository;

	@Autowired
	public GameServerServiceImpl(GameServerRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<GameServer> getAll() {
		return repository.findAll();
	}

}
