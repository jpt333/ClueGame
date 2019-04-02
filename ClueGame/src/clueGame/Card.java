//Authors: Michael Berg and Jennifer Phan
package clueGame;

public class Card {
	private String cardName;
	private String initital;
	CardType cardType;
	
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
	public Card(String cardName, CardType cardType, String initital) {
		this.initital = initital;
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

	public char getInitial() {
		return initital.charAt(0);
	}
	
	@Override
	public String toString() {
		return cardName;
	}
	
}
