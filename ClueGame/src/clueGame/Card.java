//Authors: Michael Berg and Jennifer Phan
package clueGame;

public class Card {
	private String cardName;
	CardType cardType;
	
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}

	//fix this
	public boolean equals() {
		return false;
	}
	
	public String getCardName() {
		return cardName;
	}

	public CardType getCardType() {
		return cardType;
	}

	@Override
	public String toString() {
		return cardName;
	}
	
}
