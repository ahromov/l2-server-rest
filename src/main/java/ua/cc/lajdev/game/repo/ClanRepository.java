package ua.cc.lajdev.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.cc.lajdev.game.model.Clan;

public interface ClanRepository extends JpaRepository<Clan, Long> {

	@Query("SELECT COUNT(c) from Clan c")
	Long countClans();

}
