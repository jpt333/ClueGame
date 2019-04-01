//Authors: Michael Berg and Jennifer Phan
package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
public class GameActionTests {
	
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		//load deck of cards
		board = Board.getInstance();
		board.setConfigFiles("ClueBoard.csv", "rooms.txt");
		board.setCardFiles("weapons.txt", "Person.txt");
		board.initialize();
		
	}
	
	//Computer Player
	@Test
	public void testSelectATargetSelection() { 
		//if no rooms in list, select randomly
		//if room in list not just visited, must select
		//if room just visited in list, each target(including room) selected randomly
		
	}
	
	//Board
	@Test
	public void testCheckAccusation() {
		//solution that is correct
		//solution with wrong person
		//solution with wrong weapon
		//solution with wrong room
	}
	
	//Player
	@Test
	public void testDisprovingSuggestion() {
		//if player has only one matching card, it should be returned
		//if player has >1 matching card, returned card is random. selec.
		//if player has no matching cards, null is returned
	}
	
	//Board
	@Test
	public void testHandlingSuggestion() {
		//suggestion no one can disprove returns null
		//suggestion only accusing player can disprove returns null
		//suggestion only human can disprove returns answer
		//sugg. only human can disprove, but human accuser, return null
		//sugg. that 2 players disprove, correct player (based on starting with next player in list) return answer
		//sugg. human and another player can disprove, other player is next in list, ensure other player returns answer
	}
	
	//ComputerPlayer
	@Test
	public void testCreatingSuggestion() {
		//room matches current location
		//if only one weapon not seen, it's selected
		//if only one person not seen, it's selected
		//if multiple weapons not seen, one of them random selec.
		//if multiple people not seem, one of them random. selec.

	}
	
	
	
}
