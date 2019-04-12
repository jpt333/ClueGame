//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Player{
	private String playerName;
	private Color color;
	private Point pixel;
	protected Set<Card> cards;
	protected BoardCell currentLocation;
	
	public final int WIDTH = 26;
	public final int HEIGHT = 26;
	public final int CENTER_FACTOR = 7;
	
	public Player(String playerName, BoardCell currentLocation, Color color) {
		this.playerName = playerName;
		this.currentLocation = currentLocation;
		this.color = color;
	}

	public void draw(Graphics g) {
		//Draw character
		pixel = currentLocation.getCord();
		g.setColor(color);
		g.fillOval(pixel.x + CENTER_FACTOR, pixel.y, WIDTH, HEIGHT);
	}

	
	public Card disproveSuggestion(Solution suggestion) {
		Set<Card> cardsMatching = new HashSet<>();
		for(Card cardsLoc: cards) {
			if(suggestion.person == cardsLoc) { cardsMatching.add(cardsLoc); }
			if(suggestion.room == cardsLoc)   { cardsMatching.add(cardsLoc); }
			if(suggestion.weapon == cardsLoc) { cardsMatching.add(cardsLoc); }
		}
		Card carddArray[] = new Card[cardsMatching.size()];
		if(cardsMatching.size() == 1) {
			return cardsMatching.toArray(carddArray)[0];
		}
		else if(cardsMatching.size() > 1) {
			Random rand = new Random();
			int randomNum = rand.nextInt(cardsMatching.size());
			return cardsMatching.toArray(carddArray)[randomNum];
		}
		return null;
	}
	
	public Solution createSuggestion(CardDeck availableCards) {
		//don't want to modify the available cards
		CardDeck locAvailableCards = new CardDeck(availableCards);
		
		Solution answer = new Solution();
		
		/*
		 * When a computer player is constructed the room cards that 
		 *they have are added to the visited list to prevent bad 
		 *suggestions
		 */
		for(Card locCards: cards) {
			Iterator<Card> pwrCardsAvailable;
			//check if people are equal
			if(locCards.getCardType() == CardType.PERSON){
				pwrCardsAvailable = locAvailableCards.people.iterator();
				while(pwrCardsAvailable.hasNext()){
					Card next = pwrCardsAvailable.next();
					if(next.getCardName().equals(locCards.getCardName())) {
						pwrCardsAvailable.remove();
					}
				}
			}
			//check if weapons are equal
			if(locCards.getCardType() == CardType.WEAPON){		
				pwrCardsAvailable = locAvailableCards.weapons.iterator();
				while(pwrCardsAvailable.hasNext()){
					Card next = pwrCardsAvailable.next();
					if(next.getCardName().equals(locCards.getCardName())) {
						pwrCardsAvailable.remove();
					}
				}
			}
			//check if rooms are equal
			if(locCards.getCardType() == CardType.ROOM){
				pwrCardsAvailable = locAvailableCards.rooms.iterator();
				while(pwrCardsAvailable.hasNext()){
					Card next = pwrCardsAvailable.next();
					if(next.getCardName().equals(locCards.getCardName())) {
						pwrCardsAvailable.remove();
					}
				}
			}
		}
		Random rand = new Random();
		
		for(Card locCards: availableCards.rooms) {
			//add the room card
			if(locCards.getCardType() == CardType.ROOM){
				if(currentLocation.getInitial() == locCards.getInitial()){
					answer.room = locCards;
				}	
			}
		}
		
		if(answer.room == null){
			return null;
		}
		
		Card carddArray[] = new Card[locAvailableCards.people.size()];
		//randomly select a person card
		locAvailableCards.people.toArray(carddArray);
		int randomNum = rand.nextInt(locAvailableCards.people.size());
		answer.person = carddArray[randomNum];
		
		carddArray = new Card[locAvailableCards.weapons.size()];
		//randomly select a weapon card
		locAvailableCards.weapons.toArray(carddArray);
		randomNum = rand.nextInt(locAvailableCards.weapons.size());
		answer.weapon = carddArray[randomNum];
		
		
		return answer;
	}
	
	public void setCurrentLocation(BoardCell currentLocation){
		this.currentLocation = currentLocation;
	}
	
	public void setCards(Set<Card> cardSet) {
		cards = cardSet;	
	}
}
