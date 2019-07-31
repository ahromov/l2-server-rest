package hromov.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hromov.game.repo.CharRepository;

@Service
public class CharService {

    @Autowired
    private CharRepository repository;

    public Integer countOnlineChars() {
	return repository.countOnlineChars();
    }

}
