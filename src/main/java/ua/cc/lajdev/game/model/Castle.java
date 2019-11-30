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

    @Column(name = "taxPercent")
    private Integer taxPercent;

    @Column(name = "treasury")
    private Integer treasury;

    @Column(name = "siegeDate")
    private Long siegeDate;

    public Castle() {

    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public Integer getTaxPercent() {
	return taxPercent;
    }

    public Integer getTreasury() {
	return treasury;
    }

    public Long getSiegeDate() {
	return siegeDate;
    }

}
