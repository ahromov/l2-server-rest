package hromov.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "gameservers")
public class GameServer {

    @Id
    @Column(name = "server_id")
    private Integer id;

    public GameServer() {
    }

    public Integer getId() {
	return id;
    }

}
