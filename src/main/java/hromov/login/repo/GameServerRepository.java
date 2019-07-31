package hromov.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hromov.login.model.GameServer;

public interface GameServerRepository extends JpaRepository<GameServer, Integer> {

}
