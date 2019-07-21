package ua.com.zzz.hromov.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.zzz.hromov.game.model.Char;

public interface CharRepository extends JpaRepository<Char, Integer> {

    @Query("SELECT COUNT(*) from characters where online = 1")
    Integer countOnlineChars();

}
