package hromov.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.model.Castle;
import hromov.game.repo.CastleRepository;

@Service
public class CastleService {

    @Autowired
    private CastleRepository castleRepository;

    public Castle getById(Integer id) {
	return castleRepository.getOne(id);
    }

}
