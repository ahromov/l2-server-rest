package hromov.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hromov.game.model.Clan;

public interface ClanRepository extends JpaRepository<Clan, Integer> {

    @Query("SELECT COUNT(*) from clan_data")
    Long countClans();

    @Query("SELECT b from clan_data b where b.hasCastle = :id")
    Clan getByCastleId(@Param("id") Integer id);

}
