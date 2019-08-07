package hromov.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "castle")
public class Castle {

    @Id
    @Column
    private Integer id;

    @Column(name = "name")
    private String name;

    public Castle() {

    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

}
