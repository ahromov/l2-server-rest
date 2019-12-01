package ua.cc.lajdev.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.login.model.GameServer;
import ua.cc.lajdev.login.repo.GameServerRepository;

@Service
public class GameServerService {

    @Autowired
    private GameServerRepository repository;

    public List<GameServer> getAll() {
	return repository.findAll();
    }

}
