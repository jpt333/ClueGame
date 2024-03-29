//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	Set<Character> roomsVisited;
	BoardCell justVisited;
	
	public ComputerPlayer(String playerName, BoardCell currentLocation, Color color, Set<Card> cardsIn) {
		super(playerName, currentLocation, color);
		roomsVisited = new HashSet<>();
		cards = cardsIn;
	}

	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		
		for(BoardCell targetsLoc: targets) {
			
			//check if it is a room
			if(targetsLoc.isRoom()) {
				//
				if(roomsVisited.contains(targetsLoc.getInitial())) {
					//contains the room
					continue;
				}else {
					//doesnt contain the room
					roomsVisited.add(targetsLoc.getInitial());
					return targetsLoc;
				}
			}
		}
		
		BoardCell targetArray[] = new BoardCell[targets.size()];
		
		Random rand = new Random();
		int adress = 0;
		
		for(BoardCell targetsLoc: targets) {targetArray[adress] = targetsLoc; adress++; }
		int randomNum = rand.nextInt(targets.size());
		
		return targetArray[randomNum];
	}
	
	//pass in all cards from the board
	//does not have to be in room to make suggestion
	public Solution makeAccusation(CardDeck availableCards) {
		//don't want to modify the available cards
				CardDeck locAvailableCards = availableCards;
				Solution answer = new Solution();
				/*
				 * When a computer player is constructed the room cards that 
				 *they have are added to the visited list to prevent bad 
				 *suggestions
				 */
				
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
				}
				
				Random rand = new Random();
				
				Card carddArray[] = new Card[locAvailableCards.rooms.size()];
				//randomly select a person card
				locAvailableCards.rooms.toArray(carddArray);
				int randomNum = rand.nextInt(locAvailableCards.rooms.size());
				answer.room = carddArray[randomNum];
				
				carddArray = new Card[locAvailableCards.people.size()];
				//randomly select a person card
				locAvailableCards.people.toArray(carddArray);
				randomNum = rand.nextInt(locAvailableCards.people.size());
				answer.person = carddArray[randomNum];
				
				carddArray = new Card[locAvailableCards.weapons.size()];
				//randomly select a weapon card
				locAvailableCards.weapons.toArray(carddArray);
				randomNum = rand.nextInt(locAvailableCards.weapons.size());
				answer.weapon = carddArray[randomNum];
				return answer;
	}
	
	//pass in all cards from the board 
	//has to be in a room to make suggestion
	@Override
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
	
	@Override
	public Card disproveSuggestion(Solution suggestion) {
		Set<Card> cardsMatching = new HashSet<>();
		for(Card cardsLoc: cards) {
			if(suggestion.person.getCardName().equals(cardsLoc.getCardName())) { 
				cardsMatching.add(cardsLoc); 
			}	
			if(suggestion.room.getCardName().equals(cardsLoc.getCardName()))   { 
				cardsMatching.add(cardsLoc); 
			}
			if(suggestion.weapon.getCardName().equals(cardsLoc.getCardName())) { 
				cardsMatching.add(cardsLoc); 
			}
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

	public Set<Character> getRoomsVisited() {
		return roomsVisited;
	}
	
	@Override
	public void setCards(Set<Card> cardSet) {
		roomsVisited.clear();
		cards = cardSet;	
		for(Card cardsLoc : cards) {
			if(cardsLoc.getCardType() == CardType.ROOM) {
				roomsVisited.add(cardsLoc.getInitial());
			}
		} 
		
	}
	
}
