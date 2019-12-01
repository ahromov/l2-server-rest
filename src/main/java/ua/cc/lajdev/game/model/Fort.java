package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "fort")
public class Fort {

    @Id
    @Column
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "siegeDate")
    private Long siegeDate;

    @Column(name = "owner")
    private Integer owner;

    public Fort() {

    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public Long getSiegeDate() {
	return siegeDate;
    }

    public Integer getOwner() {
	return owner;
    }

}
