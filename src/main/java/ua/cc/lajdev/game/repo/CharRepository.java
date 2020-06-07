package ua.cc.lajdev.game.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.cc.lajdev.game.model.Char;

public interface CharRepository extends JpaRepository<Char, Integer> {

	@Query("SELECT COUNT(*) from characters where online = 1 and accesslevel = 0")
	Integer getOnlineNoneGmChars();

	@Query("SELECT COUNT(*) from characters where accesslevel = 0")
	Long countAllChars();

	@Query("SELECT COUNT(*) from characters where nobless = 1 and accesslevel = 0")
	Long countNoblessChars();

	@Query("SELECT COUNT(*) from characters where accesslevel = 8")
	Long countGmChars();

	@Query(value = "SELECT * FROM characters WHERE accesslevel = 0 AND nobless = 1 ORDER BY onlinetime DESC LIMIT 10", nativeQuery = true)
	List<Char> getTop10Chars();

}
