package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

	Set<BoardCell> roomsVisited;
	
	public ComputerPlayer(String playerName, int row, int column, Color color) {
		super(playerName, row, column, color);
		roomsVisited = new HashSet<>();
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		for(BoardCell targetsLoc: targets) {
			//check if it is a room
			if(targetsLoc.isRoom()) {
				//check to see if it has been visited
				for(BoardCell roomsVisitedLoc: roomsVisited) {
					if(roomsVisitedLoc.getInitial() == roomsVisitedLoc.getInitial()) {
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
	
	public void makeAccusation() {
		
	}
	
	public void createSuggestion() {
		
	}
}
