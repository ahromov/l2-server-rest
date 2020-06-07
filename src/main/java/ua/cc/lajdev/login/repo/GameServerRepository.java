package ua.cc.lajdev.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.cc.lajdev.login.model.GameServer;

public interface GameServerRepository extends JpaRepository<GameServer, Integer> {

}
