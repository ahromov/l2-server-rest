package ua.cc.lajdev.game.model;

public enum Rates {

	DeathDropChanceMultiplier("Drop"), CorpseDropChanceMultiplier("Spoil"),
	RaidDropChanceMultiplier("Raid"), DropAmountMultiplierByItemId("Adena"), RateXp("XP"),
	RateSp("SP"), RateQuestRewardXP("Quest reward XP"), RateQuestDrop("Quest drop items");

	private String propertyName;

	Rates(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
