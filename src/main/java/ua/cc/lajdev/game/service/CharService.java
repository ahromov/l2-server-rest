package ua.cc.lajdev.game.service;

import java.util.List;

import ua.cc.lajdev.game.model.PlayersChar;

public interface CharService {

	public Integer getOnlineNoGm();

	public Long countAll();

	public Long countNobless();

	public Long countGms();

	public List<PlayersChar> getTop10();

}
