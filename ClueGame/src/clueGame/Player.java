//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private String playerName;
	BoardCell currentLocation;
	private Color color;
	protected Set<Card> cards;
	
	public Player(String playerName, BoardCell currentLocation, Color color) {
		this.playerName = playerName;
		this.currentLocation = currentLocation;
		this.color = color;
	}

	public void setCards(Set<Card> cardSet) {
		cards = cardSet;
	}
}
