//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	ArrayList<Character> roomsVisited;
	
	public ComputerPlayer(String playerName, BoardCell currentLocation, Color color) {
		super(playerName, currentLocation, color);
		roomsVisited = new ArrayList<>();
	}

	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		for(BoardCell targetsLoc: targets) {
			//check if it is a room
			if(targetsLoc.isRoom()) {
				//check to see if it has been visited
				for(Character roomsVisitedLoc: roomsVisited) {
					if(targetsLoc.getInitial() == roomsVisitedLoc) {
						continue;
					}
				}
				//add to visited room and return
				roomsVisited.add(targetsLoc.getInitial());
				
				return targetsLoc;
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
					}
				}
				Random rand = new Random();
				
				Card carddArray[] = new Card[locAvailableCards.rooms.size()];
				//randomly select a person card
				locAvailableCards.rooms.toArray(carddArray);
				int randomNum = rand.nextInt(locAvailableCards.rooms.size());
				answer.person = carddArray[randomNum];
				
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
		CardDeck locAvailableCards = availableCards;
		Solution answer = new Solution();
		/*
		 * When a computer player is constructed the room cards that 
		 *they have are added to the visited list to prevent bad 
		 *suggestions
		 */
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
	
	@Override
	public Card disproveSuggestion(Solution suggestion) {
		Card carddArray[] = null;
		Set<Card> cardsMatching = new HashSet<>();
		for(Card cardsLoc: cards) {
			if(suggestion.person == cardsLoc) { cardsMatching.add(cardsLoc); }
			if(suggestion.room == cardsLoc)   { cardsMatching.add(cardsLoc); }
			if(suggestion.weapon == cardsLoc) { cardsMatching.add(cardsLoc); }
		}
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

	public ArrayList<Character> getRoomsVisited() {
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
