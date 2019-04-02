//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	Set<BoardCell> roomsVisited;

	
	public ComputerPlayer(String playerName, BoardCell currentLocation, Color color) {
		super(playerName, currentLocation, color);
		roomsVisited = new HashSet<>();
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		for(BoardCell targetsLoc: targets) {
			//check if it is a room
			if(targetsLoc.isRoom()) {
				//check to see if it has been visited
				for(BoardCell roomsVisitedLoc: roomsVisited) {
					if(targetsLoc.getInitial() == roomsVisitedLoc.getInitial()) {
						continue;
					}
				}
				//add to visited room and return
				roomsVisited.add(targetsLoc);
				
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
	public Solution makeAccusation(Set<Card> availableCards) {
		//don't want to modify the available cards
		Set<Card> locAvailableCards = availableCards;
		return null;
	}
	
	//pass in all cards from the board 
	//has to be in a room to make suggestion
	public Solution createSuggestion(CardDeck availableCards) {
		//don't want to modify the available cards
		CardDeck locAvailableCards = availableCards;
		
		//todo create test if in a room
		if(/*inroom*/true) {
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
		return null;
	}
	
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
}
