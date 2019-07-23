package ua.com.zzz.hromov.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gameservers")
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
