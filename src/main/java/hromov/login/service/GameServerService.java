package hromov.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.login.model.GameServer;
import hromov.login.repo.GameServerRepository;

@Service
public class GameServerService {

    @Autowired
    private GameServerRepository repository;

    public List<GameServer> getAll() {
	return repository.findAll();
    }

}
