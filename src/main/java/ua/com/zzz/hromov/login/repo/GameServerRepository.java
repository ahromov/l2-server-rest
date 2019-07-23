package ua.com.zzz.hromov.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.zzz.hromov.login.model.GameServer;

public interface GameServerRepository extends JpaRepository<GameServer, Integer> {

}
