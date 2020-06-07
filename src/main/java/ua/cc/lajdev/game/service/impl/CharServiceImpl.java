package ua.cc.lajdev.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.Char;
import ua.cc.lajdev.game.repo.CharRepository;
import ua.cc.lajdev.game.service.CharService;

@Service
public class CharServiceImpl implements CharService {

	@Autowired
	private CharRepository repository;

	@Override
	public Integer getOnlineNoGm() {
		return repository.getOnlineNoneGmChars();
	}

	@Override
	public Long countAll() {
		return repository.countAllChars();
	}

	@Override
	public Long countNobless() {
		return repository.countNoblessChars();
	}

	@Override
	public Long countGms() {
		return repository.countGmChars();
	}

	@Override
	public List<Char> getTop10() {
		return repository.getTop10Chars();
	}

}
