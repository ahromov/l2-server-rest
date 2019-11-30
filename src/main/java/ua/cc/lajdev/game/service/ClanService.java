package hromov.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.model.Clan;
import hromov.game.repo.ClanRepository;

@Service
public class ClanService {

    @Autowired
    private ClanRepository repository;

    public Clan getById(Integer id) {
	return repository.getOne(id);
    }

    public Long countClans() {
	return repository.countClans();
    }

    public List<Clan> getClans() {
	return repository.findAll();
    }

    public Clan getByCastleId(Integer id) {
	return repository.getByCastleId(id);
    }

}
