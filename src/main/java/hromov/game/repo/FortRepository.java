package hromov.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hromov.game.model.Fort;

@Repository
public interface FortRepository extends JpaRepository<Fort, Integer> {

}
