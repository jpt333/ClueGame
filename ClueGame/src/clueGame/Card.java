package clueGame;

public class Card {
	private String cardName;
	
	public Card(String cardName) {
		this.cardName = cardName;
	}

	public boolean equals() {
		return false;
	}

	@Override
	public String toString() {
		return "cardName";
	}
	
}
