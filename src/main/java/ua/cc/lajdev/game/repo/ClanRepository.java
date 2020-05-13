package ua.cc.lajdev.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.cc.lajdev.game.model.Clan;

public interface ClanRepository extends JpaRepository<Clan, Integer> {

	@Query("SELECT COUNT(*) from clan_data")
	Long countClans();

}
