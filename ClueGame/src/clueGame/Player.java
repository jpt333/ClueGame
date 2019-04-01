package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private Set<Card> cards;
	
	public Player(String playerName, int row, int column, Color color) {
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
	}

	public void setCards(Set<Card> cardSet) {
		cards = cardSet;
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		for(Card cardsLoc: cards) {
			if(suggestion.person == cardsLoc) { return cardsLoc; }
			if(suggestion.room == cardsLoc)   { return cardsLoc; }
			if(suggestion.weapon == cardsLoc) { return cardsLoc; }
		}
		return null;
	}
}
