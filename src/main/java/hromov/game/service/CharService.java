package hromov.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.model.Char;
import hromov.game.repo.CharRepository;

@Service
public class CharService {

    @Autowired
    private CharRepository repository;

    public Char getById(Integer id) {
	return repository.getOne(id);
    }

    public Integer countOnlineChars() {
	return repository.countOnlineChars();
    }

    public Long countAllChars() {
	return repository.countAllChars();
    }

    public Long countNoblessChars() {
	return repository.countNoblessChars();
    }

    public Long countGmChars() {
	return repository.countGmChars();
    }

    public List<Char> getAllByClanId(Integer id) {
	return repository.getAllByClanId(id);
    }

    public List<Char> getTop10Chars() {
	return repository.getTop10Chars();
    }

}
