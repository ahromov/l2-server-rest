package hromov.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.model.Clan;
import hromov.game.repo.ClanRepository;

@Service
public class ClanService {

    @Autowired
    private ClanRepository clanRepository;

    public Clan getById(Integer id) {
	return clanRepository.getOne(id);
    }

    public Long countClans() {
	return clanRepository.countClans();
    }

    public List<Clan> getClans() {
	return clanRepository.findAll();
    }

}
