//Authors: Michael Berg and Jennifer Phan
package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.CardType;

//Test loading people, load/create deck of cards, and dealing cards
public class GameSetupTests {
	public final int MAX_DECK_OF_CARDS = 21;
	public final int MAX_ROOM_CARDS = 9;
	public final int MAX_WEAPONS = 6;
	public final int MAX_PERSON = 6;
	
	@BeforeClass
	public static void setUp() {
		
	}
	
	//Checks correct total number of cards
	@Test
	public void testNumberOfCardsInDeck() {
		assertEquals(24, /*TBD*/);
	}
	
	//Checks correct number of each type of card
	@Test
	public void testTypeOfCard() {
		if(/*PERSON*/) {
			assertEquals(6, );
		}
		if(/*WEAPON*/) {
			assertEquals(6, );
		}
		if(/*Room*/) {
			assertEquals(9, );
		}
	}
	
	//Checks that there is a room, weapon, and person in deck
	@Test
	public void testHasCard() {
		boolean person = false;
		boolean weapon = false;
		boolean room = false;
		
		//for loop to check that there is at least 1
		
		assertTrue(person);
		assertTrue(weapon);
		assertTrue(room);
		
	}
	
	//Test that all players have roughly same number of cards after dealing
	@Test
	public void testDealingCards() { 
		//Also test that same card not given to more than 1 player
	}
	
	
}
