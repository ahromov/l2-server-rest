package ua.com.zzz.hromov.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.zzz.hromov.login.model.GameServer;
import ua.com.zzz.hromov.login.repo.GameServerRepository;

@Service
public class GameServerService {

    @Autowired
    private GameServerRepository repository;

    public List<GameServer> getAll() {
	return repository.findAll();
    }

}
