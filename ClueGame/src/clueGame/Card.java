//Authors: Michael Berg and Jennifer Phan
package clueGame;

public class Card {
	private String cardName;
	private String initital;
	private CardType cardType;
	
	//this constructor is for weapons and people
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
	//this constructor is for rooms
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
