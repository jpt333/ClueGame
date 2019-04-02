package clueGame;

import java.util.HashSet;
import java.util.Set;

public class CardDeck {
	
	public CardDeck() {
		weapons = new HashSet<>();
		people = new HashSet<>();
		rooms = new HashSet<>();
	}
	
	public Set<Card> weapons;
	public Set<Card> people;
	public Set<Card> rooms;
}
