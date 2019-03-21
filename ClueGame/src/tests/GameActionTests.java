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
	
	@Test
	public void testSelectATargetSelection() {
		
	}
	
	@Test
	public void testCheckAccusation() {
		
	}
	
	@Test
	public void testDisprovingSuggestion() {
		
	}
	
	@Test
	public void testHandlingSuggestion() {
		
	}
	
	@Test
	public void testCreatingSuggestion() {
		
	}
	
	
	
}
