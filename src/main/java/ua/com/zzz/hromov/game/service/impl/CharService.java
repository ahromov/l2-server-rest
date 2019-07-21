package ua.com.zzz.hromov.game.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.zzz.hromov.game.repo.CharRepository;
import ua.com.zzz.hromov.game.service.ICharService;

@Service
public class CharService implements ICharService {

    @Autowired
    private CharRepository repository;

    @Override
    public Integer countOnlineChars() {
	return repository.countOnlineChars();
    }

}
