package hromov.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.game.model.Castle;

@Repository
public interface CastleRepository extends JpaRepository<Castle, Integer> {

}
