package clueGame;

import java.io.File;

public class AssestManager {
	
	public String getDicePath(int number) {
		//gets the dice number sure it exists then returns the path
		File diceLocation = new File("/Resources/Dice/" + number + ".png");
		if(diceLocation.exists()) {return diceLocation.getPath();}
		
		//dice number not found
		File missingDice = new File("/Resources/Miscellaneous/Missing Dice.png");
		if(missingDice.exists()) {return missingDice.getPath();}
		
		//something is terribly wrong if this happens
		return null;
	}
	
	public String getCardPath(String cardName, CardType type) {
		//gets the card name makes sure it exists then returns the path
		if(type == CardType.PERSON) {
			File cardLocation = new File("/Resources/People/" + cardName + ".png");
			if(cardLocation.exists()) {return cardLocation.getPath();}
		}
		if(type == CardType.ROOM) {
			File cardLocation = new File("/Resources/Rooms/" + cardName + ".png");
			if(cardLocation.exists()) {return cardLocation.getPath();}
		}
		if(type == CardType.WEAPON) {
			File cardLocation = new File("/Resources/Weapons/" + cardName + ".png");
			if(cardLocation.exists()) {return cardLocation.getPath();}
		}
		File missingCard = new File("/Resources/Miscellaneous/Missing Card.png");
		if(missingCard.exists()) {return missingCard.getPath();}
		
		//something is terribly wrong if this happens
		return null;
	}
}
