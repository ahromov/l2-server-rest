package ua.cc.lajdev.game.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.PlayersChar;
import ua.cc.lajdev.game.repo.PlayersCharRepository;
import ua.cc.lajdev.game.service.CharService;

@Service
public class CharServiceImpl implements CharService {

	@Autowired
	private PlayersCharRepository repository;

	@Override
	public Integer getOnlineNoGm() {
		return repository.getOnlineNoGmChars();
	}

	@Override
	public Long countAll() {
		return repository.countAllNoGmChars();
	}

	@Override
	public Long countNobless() {
		return repository.countNoblessNoGmChars();
	}

	@Override
	public Long countGms() {
		return repository.countGmChars();
	}

	@Override
	public List<PlayersChar> getTop10() {
		Pageable pageWithThreeNews = PageRequest.of(0, 10, Sort.by("onlineTime").descending());

		return repository.findAll(pageWithThreeNews).getContent().stream().filter(x -> x.getAccesslevel() == 0)
				.collect(Collectors.toList());
	}

}
