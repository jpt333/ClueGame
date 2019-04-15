package clueGame;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AssetsManager {
	
	public void setAsset(JLabel assetLocation, int number) {
		//this is the easiest way to just catch a null pointer at the moment
		//refactor when possible by checking if file exists 
		
		//---------------------------------------------------------------------------------------------------
				try {
					assetLocation.setIcon(new ImageIcon(ClueGameGUI.class.getResource(getDicePath(number))));
				}
				catch (NullPointerException e) {
					//set the missing dice file
					assetLocation.setIcon(new ImageIcon(ClueGameGUI.class.getResource(getDicePath(7))));
				}
				//---------------------------------------------------------------------------------------------------
	}
	
	public void setAsset(JLabel assetLocation, String cardName, CardType type) {
		//this is the easiest way to just catch a null pointer at the moment
		//refactor when possible by checking if file exists 
		
		
		//---------------------------------------------------------------------------------------------------
		try {
			assetLocation.setIcon(new ImageIcon(ClueGameGUI.class.getResource(getCardPath(cardName, type))));
		}
		catch (NullPointerException e) {
			//set the missing card file
			assetLocation.setIcon(new ImageIcon(ClueGameGUI.class.getResource(getCardPath(null, null))));
		}
		//---------------------------------------------------------------------------------------------------
		
		
	}
	
	private String getDicePath(int number) {
		if(number <=0 || number > 6) {
			//dice number not found
			return "/Resources/Miscellaneous/Missing Dice.png";
		}
		//gets the dice number sure it exists then returns the path
		return "/Resources/Dice/" + number + ".png";
	}
	
	private String getCardPath(String cardName, CardType type) {
		//gets the card name makes sure it exists then returns the path
		if(type == null) {
			return "/Resources/Miscellaneous/Missing Card.png";
		}
		if(type == CardType.PERSON) {
			return "/Resources/People/" + cardName + ".png";
		}
		if(type == CardType.ROOM) {
			return "/Resources/Rooms/" + cardName + ".png";
		}
		if(type == CardType.WEAPON) {
			return "/Resources/Weapons/" + cardName + ".png";
		}
		
		//unreachable
		return null;
	}
}
