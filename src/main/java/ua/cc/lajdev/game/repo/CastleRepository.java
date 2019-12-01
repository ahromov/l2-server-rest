package ua.cc.lajdev.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.game.model.Castle;

@Repository
public interface CastleRepository extends JpaRepository<Castle, Integer> {

}
