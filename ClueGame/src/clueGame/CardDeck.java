package clueGame;

import java.util.HashSet;
import java.util.Set;

public class CardDeck {
	
	public CardDeck() {
		weapons = new HashSet<>();
		people = new HashSet<>();
		rooms = new HashSet<>();
	}
	
	public CardDeck(CardDeck copyInfo) {
		this.weapons = new HashSet<>(copyInfo.weapons);
		this.people = new HashSet<>(copyInfo.people);
		this.rooms = new HashSet<>(copyInfo.rooms);
	}

	public Set<Card> weapons;
	public Set<Card> people;
	public Set<Card> rooms;
}
