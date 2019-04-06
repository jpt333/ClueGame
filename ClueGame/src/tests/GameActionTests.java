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
import clueGame.DoorDirection;
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
		board.dealCards();
	}
	
	//Computer Player this sometimes passes and sometimes doesn't
	@Test
	public void testSelectATargetSelection() { 
		Color Yellow = Color.YELLOW;
		//Room 1 distance away
		ComputerPlayer player = new ComputerPlayer("Colonel Mustard", board.getCellAt(0, 3), Yellow);
		//if no rooms in list, select randomly
		board.calcTargets(0,3,1);
		Set<BoardCell> targets = board.getTargets();
		boolean location_1_3 = false;
		boolean location_0_4 = false;
		  //check that it chose each of them at least once
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
		board.calcTargets(8,3,1); //has distance of 1
		Set<BoardCell> targetss = board.getTargets();
		
		//TESTING!!!!!!!!!!!!!!!!!
		//Bedroom exists in list
		System.out.println(targetss.contains(board.getCellAt(8, 2)));

		BoardCell select = player.pickLocation(targetss);
		//System.out.println(select.getInitial()); //IT"S NOT SELECTING ROOM
		System.out.println(select);

		//System.out.print(select.isWalkway());
		//System.out.print(select.isRoom());
		System.out.print(select);
		//OR ISROOM() ISN"T WORKING!!!!!!!
		//should initial be a char?
		
		assertEquals(select, board.getCellAt(8,2));
		
		//if room just visited in list, each target(including room) selected randomly
		select = player.pickLocation(targetss);
		board.calcTargets(8,4,2);
		Set<BoardCell> targets2 = board.getTargets();
		System.out.println(select);
		
	}
	
	//Board			//DONE
	@Test
	public void testCheckAccusation() {
		Solution answer = board.getSolution();
		Solution suggestion = new Solution();
		
		//solution that is correct 
		
		suggestion.person = answer.getPerson();
		suggestion.room = answer.getRoom();
		suggestion.weapon = answer.getWeapon();
		
		assertTrue(board.checkAccusation(suggestion));
		
		assertEquals(answer.person, suggestion.person);
		assertEquals(answer.room, suggestion.room);
		assertEquals(answer.weapon, suggestion.weapon);
		
		//solution with wrong person
		Card wrongPerson = new Card("Professor P", CardType.PERSON);
		suggestion.person = wrongPerson;
		//assertNotEquals(answer.person, suggestion.person);
		assertFalse(board.checkAccusation(suggestion));
		
		//solution with wrong weapon
		Card wrongWeapon = new Card("Wrong", CardType.WEAPON);
		suggestion.weapon = wrongWeapon;
		//assertNotEquals(answer.weapon, suggestion.weapon);
		assertFalse(board.checkAccusation(suggestion));
		
		//solution with wrong room
		Card wrongRoom = new Card("BedRoom", CardType.ROOM, "B" );
		suggestion.room = wrongRoom;
		//assertNotEquals(answer.room, suggestion.room);
		assertFalse(board.checkAccusation(suggestion));
	}
	
	//Player      //DONE
	@Test
	public void testDisprovingSuggestion() {
		BoardCell playerloc = board.getCellAt(7, 3);
		Color yellow = Color.YELLOW;
		Player playerOne = new Player("Player One", playerloc, yellow);
		Solution suggestion = new Solution();
		Card mustard = new Card("Colonel Mustard", CardType.PERSON);
		Card weaponn = new Card("Rope", CardType.WEAPON);
		Card rooom = new Card("Kitchen", CardType.ROOM, "K");
		suggestion.person = mustard;
		suggestion.room = rooom;
		suggestion.weapon = weaponn;
		
		//if player has only one matching card, it should be returned
		
		Set<Card> cardSet = new HashSet<>();
		Card mustardd = new Card("Colonel Mustard", CardType.PERSON);
		Card weapon1 = new Card("Candlestick", CardType.WEAPON);
		Card rooom2 = new Card("Parlor", CardType.ROOM, "K");
		cardSet.add(mustardd);
		cardSet.add(weapon1);
		cardSet.add(rooom2);
		playerOne.setCards(cardSet); //Set Cards
		
		assertEquals(mustardd, playerOne.disproveSuggestion(suggestion));
		
		//if player has >1 matching card, returned card is random selec.
		//In this case, 2 matching cards, mustard and weapon
		Set<Card> cardSet2 = new HashSet<>();
		Card weapon2 = new Card("Rope", CardType.WEAPON);
		cardSet2.add(weapon2);
		cardSet2.add(mustardd);
		cardSet2.add(rooom2);
		playerOne.setCards(cardSet2);
		
		boolean weapNum = false;
		boolean mustNum = false;
		boolean roomNum = false;
		
		for(int i = 0; i < 100; i++) {
			if(playerOne.disproveSuggestion(suggestion) == weapon2) {
				weapNum = true;
			}
			if(playerOne.disproveSuggestion(suggestion) == mustardd) {
				mustNum = true;
			}
			if(playerOne.disproveSuggestion(suggestion) != rooom2) {
				roomNum = true;
			}
		}
		
		assertTrue(weapNum);
		assertTrue(mustNum);
		assertTrue(roomNum);
		
		//if player has no matching cards, null is returned
		Set<Card> cardSet3 = new HashSet<>();
		Card weapon3 = new Card("Candlestick", CardType.WEAPON);
		Card scarlet = new Card("Miss Scarlet", CardType.PERSON);
		Card room3 = new Card("Game room", CardType.ROOM);
		cardSet3.add(weapon3);
		cardSet3.add(scarlet);
		cardSet3.add(room3);
		playerOne.setCards(cardSet3);
		
		assertEquals(null, playerOne.disproveSuggestion(suggestion));
	}
	
	//Board
	@Test
	public void testHandlingSuggestion() {
		Color yellow = Color.YELLOW;
		Color magenta = Color.MAGENTA;
		Color blue = Color.BLUE;
		Color green = Color.GREEN;
		Color black = Color.BLACK;
		BoardCell currentLoc = board.getCellAt(8, 2);
		Player playerHuman = new Player("playerHuman", currentLoc , black);
		currentLoc = board.getCellAt(8, 2);
		ComputerPlayer player1 = new ComputerPlayer("player1", currentLoc, yellow );
		currentLoc = board.getCellAt(22, 4);
		ComputerPlayer player2 = new ComputerPlayer("player2", currentLoc, magenta);
		currentLoc = board.getCellAt(0, 11);
		ComputerPlayer player3 = new ComputerPlayer("player3", currentLoc, blue );
		currentLoc = board.getCellAt(12, 14);
		ComputerPlayer player4 = new ComputerPlayer("player4", currentLoc, green);
		
		
		//suggestion no one can disprove returns null
		
		Set<Card> cardSet = new HashSet<Card>();
		Card kPerson = new Card("Person", CardType.PERSON);
		Card kWeapon = new Card("Weapon", CardType.WEAPON);
		Card kRoom = new Card("Room", CardType.ROOM);
		
		player1.setCards(cardSet);
		
		assertNull(board.handleSuggestion(player1));
		
		//suggestion only accusing player can disprove returns null
		 cardSet = new HashSet<Card>();
		 kPerson = new Card("Miss Scarlet", CardType.PERSON);
		 kWeapon = new Card("Candlestick", CardType.WEAPON);
		 kRoom = new Card("Library", CardType.ROOM, "L");
		
		player1.setCards(cardSet);
		
		assertNull(board.handleSuggestion(player1));
		
		//suggestion only human can disprove returns answer
		cardSet = new HashSet<Card>();
		kPerson = new Card("card", CardType.PERSON);
		kWeapon = new Card("card", CardType.WEAPON);
		kRoom = new Card("card", CardType.ROOM, "C");
		cardSet.add(kPerson);
		cardSet.add(kWeapon);
		cardSet.add(kRoom);
		player1.setCards(cardSet);
		
		assertNotNull(board.handleSuggestion(player1));
	
		
		//sugg. only human can disprove, but human accuser, return null
		
		cardSet = new HashSet<Card>();
		kPerson = new Card("Miss Scarlet", CardType.PERSON);
		kWeapon = new Card("Rope", CardType.WEAPON);
		kRoom = new Card("Library", CardType.ROOM, "L");
		cardSet.add(kPerson);
		cardSet.add(kWeapon);
		cardSet.add(kRoom);
		player1.setCards(cardSet);
		
		assertNull(board.handleSuggestion(player1));
		
		//sugg. that 2 players disprove, correct player (based on starting with next player in list) return answer
		assertNotNull(board.handleSuggestion(player2));
		//sugg. human and another player can disprove, other player is next in list, ensure other player returns answer
		assertNotNull(board.handleSuggestion(player3));
		//NOTE: reason why there is no test for these two is because we were able to check with all the players at once
	}
	
	//ComputerPlayer     //DONE
	@Test
	public void testCreatingSuggestion() {
		
		//room B
		BoardCell playerLoc = board.getCellAt(8, 2);
		Color yellow = Color.YELLOW;
		ComputerPlayer playerOne = new ComputerPlayer("Player One", playerLoc, yellow);
		
		Card weapon1 = new Card("Wrench", CardType.WEAPON);
		Card weapon2 = new Card("Candlestick", CardType.WEAPON);
		Card weapon3 = new Card("Pipe", CardType.WEAPON);
		Card weapon4 = new Card("Rope", CardType.WEAPON);
		Card weapon5 = new Card("Knife", CardType.WEAPON);
		
		Set<Card> cardSet = new HashSet<>();
		cardSet.add(weapon1);
		cardSet.add(weapon2);
		cardSet.add(weapon3);
		cardSet.add(weapon4);
		cardSet.add(weapon5);
		
		playerOne.setCards(cardSet);
		
		Solution testSuggestion = playerOne.createSuggestion(board.getDeck());
		//room matches current location
		assertTrue(testSuggestion.room.getInitial() == playerLoc.getInitial());
		//if only one weapon not seen, it's selected
		
		assertTrue(testSuggestion.weapon.getCardName().equals("Revolver"));
		cardSet.clear();
		
		Card person1 = new Card("Miss Scarlet", CardType.PERSON);
		Card person2 = new Card("Mr. Green", CardType.PERSON);
		Card person3 = new Card("Mrs. Peacock", CardType.PERSON);
		Card person4 = new Card("Mrs. White", CardType.PERSON);
		Card person5 = new Card("Professor Plum", CardType.PERSON);
		
		cardSet.add(person1);
		cardSet.add(person2);
		cardSet.add(person3);
		cardSet.add(person4);
		cardSet.add(person5);
		
		playerOne.setCards(cardSet);
		testSuggestion = playerOne.createSuggestion(board.getDeck());
		//if only one person not seen, it's selected
		assertTrue(testSuggestion.person.getCardName().equals("Colonel Mustard"));
		cardSet.clear();
		//if multiple weapons not seen, one of them random select.
		
		playerOne.setCards(cardSet);
		testSuggestion = playerOne.createSuggestion(board.getDeck());
		
		int colonelMustard = 0;
		int missScarlet = 0;
		int mrGreen = 0;
		int mrsPeacock = 0;
		int mrsWhite = 0;
		int professorPlum = 0;
		
		int wrench = 0;
		int candlestick = 0;
		int pipe = 0;
		int rope = 0;
		int revolver = 0;
		int knife = 0;
		
		for(int a1 = 0; a1 < 200; a1++){
			testSuggestion = playerOne.createSuggestion(board.getDeck());
			if(testSuggestion.person.getCardName().equals("Colonel Mustard")){ colonelMustard++;}
			if(testSuggestion.person.getCardName().equals("Miss Scarlet")){ missScarlet++;}
			if(testSuggestion.person.getCardName().equals("Mr. Green")){ mrGreen++;}
			if(testSuggestion.person.getCardName().equals("Mrs. Peacock")){ mrsPeacock++;}
			if(testSuggestion.person.getCardName().equals("Mrs. White")){ mrsWhite++;}
			if(testSuggestion.person.getCardName().equals("Professor Plum")){ professorPlum++;}
			
			if(testSuggestion.weapon.getCardName().equals("Wrench")){ wrench++;}
			if(testSuggestion.weapon.getCardName().equals("Candlestick")){ candlestick++;}
			if(testSuggestion.weapon.getCardName().equals("Pipe")){ pipe++;}
			if(testSuggestion.weapon.getCardName().equals("Rope")){ rope++;}
			if(testSuggestion.weapon.getCardName().equals("Revolver")){ revolver++;}
			if(testSuggestion.weapon.getCardName().equals("Knife")){ knife++;}
		}
		
		//if multiple people not seem, one of them random. select.
		assertTrue(colonelMustard  > 10);
		assertTrue(missScarlet  > 10);
		assertTrue(mrGreen  > 10);
		assertTrue(mrsPeacock  > 10);
		assertTrue(mrsWhite  > 10);
		assertTrue(professorPlum  > 10);
		
		//if multiple weapons not seem, one of them random. select.
		assertTrue(wrench  > 20);
		assertTrue(candlestick  > 20);
		assertTrue(pipe  > 20);
		assertTrue(rope  > 20);
		assertTrue(revolver  > 20);
		assertTrue(knife  > 20);
	}
}
