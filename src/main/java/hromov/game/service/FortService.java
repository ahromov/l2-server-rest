package hromov.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.model.Fort;
import hromov.game.repo.FortRepository;

@Service
public class FortService {

    @Autowired
    private FortRepository repository;

    public List<Fort> getAll() {
	return repository.findAll();
    }

}
