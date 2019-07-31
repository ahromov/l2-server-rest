package hromov.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "characters")
public class Char {

    @Id
    @Column
    private Integer charId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "char_name")
    private String charName;

    @Column
    private String clanid;

    @Column
    private Integer online;

    public Char() {
    }

    public Integer getOnline() {
	return online;
    }

    public Integer getCharId() {
	return charId;
    }

    public String getAccountName() {
	return accountName;
    }

    public String getCharName() {
	return charName;
    }

    public String getClanid() {
	return clanid;
    }

}
