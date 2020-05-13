package ua.cc.lajdev.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "fort")
public class Fort {

	@Id
	@Column
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "siegeDate")
	private Long siegeDate;

//    @Column(name = "owner")
//    private Integer owner;

	@OneToOne
	@JoinColumn(name = "owner", referencedColumnName = "clan_id")
	private Clan clan;

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

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

//    public Integer getOwner() {
//		return owner;
//	}

}
