package ua.cc.lajdev.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Char;
import ua.cc.lajdev.game.repo.CharRepository;

@Service
public class CharService {

    @Autowired
    private CharRepository repository;

    public Char getById(Integer id) {
	return repository.getOne(id);
    }

    public Integer getOnlineNoneGmChars() {
	return repository.getOnlineNoneGmChars();
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
