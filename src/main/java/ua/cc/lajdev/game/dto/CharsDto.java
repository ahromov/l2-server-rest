package ua.cc.lajdev.game.dto;

public class CharsDto {

	public Long all;
	public Long nobles;
	public Long heroes;
	public Long gms;

	public CharsDto(Long all, Long nobles, Long heroes, Long gms) {
		super();
		this.all = all;
		this.nobles = nobles;
		this.heroes = heroes;
		this.gms = gms;
	}

}
