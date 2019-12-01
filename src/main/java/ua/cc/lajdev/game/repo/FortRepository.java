package ua.cc.lajdev.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.cc.lajdev.game.model.Fort;

@Repository
public interface FortRepository extends JpaRepository<Fort, Integer> {

}
