//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Player {
	private String playerName;
	private Color color;
	protected Set<Card> cards;
	protected BoardCell currentLocation;
	
	
	public Player(String playerName, BoardCell currentLocation, Color color) {
		this.playerName = playerName;
		this.currentLocation = currentLocation;
		this.color = color;
	}

	public Card disproveSuggestion(Solution suggestion) {
//		Set<Card> cardsMatching = new HashSet<>();
//		for(Card cardsLoc: cards) {
//			if(suggestion.person == cardsLoc) { cardsMatching.add(cardsLoc); }
//			if(suggestion.room == cardsLoc)   { cardsMatching.add(cardsLoc); }
//			if(suggestion.weapon == cardsLoc) { cardsMatching.add(cardsLoc); }
//		}
//		
//		int cardSize = cardsMatching.size();
//		Card carddArray[] = null;
//		//Card cardArray[] = new Card[cardSize];
//		if(cardsMatching.size() == 1) {
//			
//			//notify player that he must disprove and its that card
//			System.out.print(cardsMatching.toArray(carddArray)[0]);
//			return cardsMatching.toArray(carddArray)[0];
//		}
//		else if(cardsMatching.size() > 1) {
//			
//			//notify player that he must disprove and choose of the cards from the array
//			
//			Random rand = new Random();
//			int randomNum = rand.nextInt(cardsMatching.size());
//			return cardsMatching.toArray(carddArray)[randomNum];
//		}
//		return null;
		Set<Card> cardsMatching = new HashSet<>();

		for(Card cardsLoc: cards) {
			if(suggestion.getPerson().getCardName().equals(cardsLoc.getCardName())) { 
				cardsMatching.add(cardsLoc); }
			if(suggestion.getRoom().getCardName().equals(cardsLoc.getCardName()))   { 
				cardsMatching.add(cardsLoc); }
			if(suggestion.getWeapon().getCardName().equals(cardsLoc.getCardName())) { 
				cardsMatching.add(cardsLoc); }
		}
		int cardSize = cardsMatching.size();
		//Card carddArray[] = null;
		Card cardArray[] = new Card[cardSize];
		cardArray = cardsMatching.toArray(cardArray);
		if(cardsMatching.size() == 1) {
			//notify player that he must disprove and its that card
			System.out.print(cardArray[0]);
			return cardArray[0];
		}
		else if(cardsMatching.size() > 1) {
			
			//notify player that he must disprove and choose of the cards from the array
			
			Random rand = new Random();
			int randomNum = rand.nextInt(cardsMatching.size());
			return cardArray[randomNum];
		}
		return null;
	}
	
	public Solution createSuggestion(CardDeck availableCards) {
		//don't want to modify the available cards
		CardDeck locAvailableCards = availableCards;
		Solution answer = new Solution();
		
		if(currentLocation.isRoom()) {
			for(Card locCards: cards) {
				if(locAvailableCards.people.contains(locCards)) {
					locAvailableCards.people.remove(locCards);
				}
				if(locAvailableCards.weapons.contains(locCards)) {
					locAvailableCards.weapons.remove(locCards);
				}
				if(locAvailableCards.rooms.contains(locCards)) {
					locAvailableCards.rooms.remove(locCards);
				}
				
				//add the room card
				if(currentLocation.getInitial() == locCards.getInitial()){
					answer.room = locCards;
				}	
			}
		}
		
		//same behavior as the ai for now when gui is implemented
		//the random choices will be replaced by a drop down or
		//tile select screen
		Random rand = new Random();
		
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
