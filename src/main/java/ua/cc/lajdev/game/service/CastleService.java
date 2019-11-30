package hromov.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.model.Castle;
import hromov.game.repo.CastleRepository;

@Service
public class CastleService {

    @Autowired
    private CastleRepository repository;

    public Castle getById(Integer id) {
	return repository.getOne(id);
    }

    public List<Castle> getAll() {
	return repository.findAll();
    }

}
