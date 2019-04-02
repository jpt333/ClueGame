//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private String playerName;
	protected int row;
	protected int column;
	private Color color;
	protected Set<Card> cards;
	
	public Player(String playerName, int row, int column, Color color) {
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
	}

	public void setCards(Set<Card> cardSet) {
		cards = cardSet;
	}
}
