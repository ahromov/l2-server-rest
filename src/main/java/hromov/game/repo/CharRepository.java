package hromov.game.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hromov.game.model.Char;

public interface CharRepository extends JpaRepository<Char, Integer> {

    @Query("SELECT COUNT(*) from characters where online = 1")
    Integer countOnlineChars();

    @Query("SELECT COUNT(*) from characters")
    Long countAllChars();

    @Query("SELECT COUNT(*) from characters where nobless = 1")
    Long countNoblessChars();

    @Query("SELECT COUNT(*) from characters where accesslevel = 8")
    Long countGmChars();

    @Query("SELECT b from characters b where b.clanid = :clanId")
    List<Char> getCharByClanId(@Param("clanId") Integer clanId);

    @Query(value = "SELECT * FROM characters ORDER BY onlinetime DESC LIMIT 10", nativeQuery = true)
    List<Char> getTop10Chars();

}
