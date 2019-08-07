package hromov.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hromov.game.model.Clan;

public interface ClanRepository extends JpaRepository<Clan, Integer> {

    @Query("SELECT COUNT(*) from clan_data")
    Long countClans();

}
