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

    private String[] serverNames = { "Bartz", "Sieghardt", "Kain", "Lionna", "Erica", "Gustin", "Devianne", "Hindemith",
	    "Teon (EURO)", "Franz (EURO)", "Luna (EURO)", "Sayha", "Aria", "Phoenix", "Chronos", "Naia (EURO)",
	    "Elhwynna", "Ellikia", "Shikken", "Scryde", "Frikios", "Ophylia", "Shakdun", "Tarziph", "Aria", "Esenn",
	    "Elcardia", "Yiana", "Seresin", "Tarkai", "Khadia", "Roien", "Kallint (Non-PvP)", "Baium", "Kamael",
	    "Beleth", "Anakim", "Lilith", "Thifiel", "Lithra", "Lockirin", "Kakai", "Cadmus", "Athebaldt", "Blackbird",
	    "Ramsheart", "Esthus", "Vasper", "Lancer", "Ashton", "Waytrel", "Waltner", "Tahnford", "Hunter", "Dewell",
	    "Rodemaye", "Ken Rauhel", "Ken Abigail", "Ken Orwen", "Van Holter", "Desperion", "Einhovant", "Shunaiman",
	    "Faris", "Tor", "Carneiar", "Dwyllios", "Baium", "Hallate", "Zaken", "Core" };

    public GameServer() {

    }

    public Integer getId() {
	return id;
    }

    public String getCurrentName() {
	return serverNames[this.id - 1];
    }

}
