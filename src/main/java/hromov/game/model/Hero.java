package hromov.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "heroes")
public class Hero {

    @Id
    @Column
    private Integer charId;

    public Hero() {
    }

}
