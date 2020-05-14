package ua.cc.lajdev.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "gameservers")
public class GameServer {

	private static final String[] serversNames = { "Bartz", "Sieghardt", "Kain", "Lionna", "Erica", "Gustin",
			"Devianne", "Hindemith", "Teon (EURO)", "Franz (EURO)", "Luna (EURO)", "Sayha", "Aria", "Phoenix",
			"Chronos", "Naia (EURO)", "Elhwynna", "Ellikia", "Shikken", "Scryde", "Frikios", "Ophylia", "Shakdun",
			"Tarziph", "Aria", "Esenn", "Elcardia", "Yiana", "Seresin", "Tarkai", "Khadia", "Roien",
			"Kallint (Non-PvP)", "Baium", "Kamael", "Beleth", "Anakim", "Lilith", "Thifiel", "Lithra", "Lockirin",
			"Kakai", "Cadmus", "Athebaldt", "Blackbird", "Ramsheart", "Esthus", "Vasper", "Lancer", "Ashton", "Waytrel",
			"Waltner", "Tahnford", "Hunter", "Dewell", "Rodemaye", "Ken Rauhel", "Ken Abigail", "Ken Orwen",
			"Van Holter", "Desperion", "Einhovant", "Shunaiman", "Faris", "Tor", "Carneiar", "Dwyllios", "Baium",
			"Hallate", "Zaken", "Core" };

	@Id
	@Column(name = "server_id")
	@JsonIgnore
	private Integer id;

	@Transient
	public String name;

	public GameServer() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PostLoad
	private void initName() {
		this.name = serversNames[this.getId() - 1];
	}

}
