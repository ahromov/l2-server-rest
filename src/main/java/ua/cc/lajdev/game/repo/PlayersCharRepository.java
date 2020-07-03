package ua.cc.lajdev.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.cc.lajdev.game.model.PlayersChar;

public interface PlayersCharRepository extends JpaRepository<PlayersChar, Integer> {

	@Query("SELECT COUNT(c) from  PlayersChar c where c.online = 1 and c.accesslevel = 0")
	Integer countOnlineNoGmChars();

	@Query("SELECT COUNT(c) from  PlayersChar c where c.accesslevel = 0")
	Long countAllNoGmChars();

	@Query("SELECT COUNT(c) from  PlayersChar c where c.nobless = 1 and c.accesslevel = 0")
	Long countNoblessNoGmChars();

	@Query("SELECT COUNT(c) from  PlayersChar c where c.accesslevel = 8")
	Long countGmChars();

}
