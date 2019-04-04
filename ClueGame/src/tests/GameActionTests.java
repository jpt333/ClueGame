//Authors: Michael Berg and Jennifer Phan
package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Solution;
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
		Color Yellow = Color.YELLOW;
		//Room 1 distance away
		ComputerPlayer player = new ComputerPlayer("Colonel Mustard",board.getCellAt(0, 3), Yellow);
		//if no rooms in list, select randomly
		board.calcTargets(0,3,1);
		Set<BoardCell> targets = board.getTargets();
		boolean location_1_3 = false;
		boolean location_0_4 = false;
		
		for(int i = 0; i < 100; i++) {
			BoardCell selected = player.pickLocation(targets);
			if(selected.getInitial() == board.getCellAt(1, 3).getInitial()) {
				location_1_3 = true;
			}
			if(selected.getInitial() == board.getCellAt(0, 4).getInitial()) {
				location_0_4 = true;
			}
		}
		assertTrue(location_1_3);
		assertTrue(location_0_4);
		
		//if room in list not just visited, must select
		player = new ComputerPlayer("Colonel Mustard", board.getCellAt(8, 3), Yellow);
		board.calcTargets(8,3,1);
		Set<BoardCell> targetss = board.getTargets();
		boolean hasRoomInList = false;
		BoardCell targ;
		for(BoardCell targetz : targetss) {
			//check if target is a room
			if(targetz.isRoom()) {
				targ  = targetz;
				hasRoomInList = true;
			}
		}
		
		ArrayList<Character> visited = new ArrayList<>();
		for(Character visit : visited) {
			if(visit == targ.getInitial()) {
				//NOT SURE HOW TO TEST
			}
		}
		
		//if room just visited in list, each target(including room) selected randomly
		
		
	}
	
	//Board
	@Test
	public void testCheckAccusation() {
		Solution answer = new Solution();
		Card mustard = new Card("Colonel Mustard", CardType.PERSON);
		Card weaponn = new Card("Rope", CardType.WEAPON);
		Card rooom = new Card("Kitchen", CardType.ROOM, "K");
		answer.person = mustard;
		answer.room = rooom;
		answer.weapon = weaponn;
		
		//solution that is correct
		Solution suggestion = new Solution();
		suggestion.person = mustard;
		suggestion.room = rooom;
		suggestion.weapon = weaponn;
		
		assertEquals(answer.person, suggestion.person);
		assertEquals(answer.room, suggestion.room);
		assertEquals(answer.weapon, suggestion.weapon);
		
		//solution with wrong person
		Card wrongPerson = new Card("Professor Plum", CardType.PERSON);
		suggestion.person = wrongPerson;
		assertNotEquals(answer.person, suggestion.person); 
		
		//solution with wrong weapon
		Card wrongWeapon = new Card("Knife", CardType.WEAPON);
		suggestion.weapon = wrongWeapon;
		assertNotEquals(answer.weapon, suggestion.weapon);
		
		//solution with wrong room
		Card wrongRoom = new Card("Bedroom", CardType.ROOM, "B" );
		suggestion.room = wrongRoom;
		assertNotEquals(answer.room, suggestion.room);
	}
	
	//Player
	@Test
	public void testDisprovingSuggestion() {
		BoardCell playerloc = board.getCellAt(7, 3);
		Color yellow = Color.YELLOW;
		Player playerOne = new Player("Player One", playerloc, yellow);
		Solution answer = new Solution();
		Card mustard = new Card("Colonel Mustard", CardType.PERSON);
		Card weaponn = new Card("Rope", CardType.WEAPON);
		Card rooom = new Card("Kitchen", CardType.ROOM, "K");
		answer.person = mustard;
		answer.room = rooom;
		answer.weapon = weaponn;
		
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
