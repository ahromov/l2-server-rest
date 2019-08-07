package hromov.game.dto;

public class CharsDto {
    public Long countAll;
    public Long countNobless;
    public Long countHeroes;
    public Long countGm;
    public Long countClans;

    public CharsDto(Long countAll, Long countNobless, Long countHeroes, Long countGm, Long countClans) {
	this.countAll = countAll;
	this.countNobless = countNobless;
	this.countHeroes = countHeroes;
	this.countGm = countGm;
	this.countClans = countClans;
    }

}
