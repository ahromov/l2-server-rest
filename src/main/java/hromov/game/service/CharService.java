package hromov.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hromov.game.model.Char;
import hromov.game.repo.CharRepository;

@Service
public class CharService {

    @Autowired
    private CharRepository charactersRepository;

    public Char getById(Integer id) {
	return charactersRepository.getOne(id);
    }

    public Integer countOnlineChars() {
	return charactersRepository.countOnlineChars();
    }

    public Long countAllChars() {
	return charactersRepository.countAllChars();
    }

    public Long countNoblessChars() {
	return charactersRepository.countNoblessChars();
    }

    public Long countGmChars() {
	return charactersRepository.countGmChars();
    }

    public List<Char> getCharByClanId(Integer id) {
	return charactersRepository.getCharByClanId(id);
    }

    public List<Char> getTop10Chars() {
	return charactersRepository.getTop10Chars();
    }

}
