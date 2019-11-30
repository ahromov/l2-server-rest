package hromov.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hromov.game.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

    @Query("SELECT COUNT(*) from heroes")
    Long countHeroes();

}
