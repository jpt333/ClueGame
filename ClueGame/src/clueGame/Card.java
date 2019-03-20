package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card(String cardName) {
		this.cardName = cardName;
		this.cardType = CardType.PERSON;
	}

	public Card(String cardName) {
		this.cardName = cardName;
	}

	public boolean equals() {
		return false;
	}
}
