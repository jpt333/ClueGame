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

//Test loading people, load/create deck of cards, and dealing cards
public class GameSetupTests {
	
	private static Board board;
	
	public final int MAX_DECK_OF_CARDS = 21;
	public final int MAX_ROOM_CARDS = 9;
	public final int MAX_WEAPONS = 6;
	public final int MAX_PERSON = 6;
	
	@BeforeClass
	public static void setUp(){
		//load deck of cards
		board = Board.getInstance();
		board.setCardFiles("weapons.txt", "Person.txt");
		board.initialize();
	}
	
	//Checks correct total number of cards
	@Test
	public void testNumberOfCardsInDeck() {
	int numCards = 0;
	Set<Card> cards = board.getCards();
	//Loop and count every card in deck
	for(Card card : cards) {
		numCards++;
	}
	assertEquals(21, numCards);
	}
	
	//Checks correct number of each type of card
	@Test
	public void testNumOfTypeOfCard() {
		
		//NEED FOR LOOP for checking the cards
		int rooms = 0;
		int person = 0;
		int weapons = 0;
		
		Set<Card> cards = board.getCards();
		
		for(Card card : cards) {
			if(card.getCardType() == CardType.PERSON) {
				person++;
			}
			if(card.getCardType() == CardType.WEAPON) {
				weapons++;
			}
			if(card.getCardType() == CardType.ROOM) {
				rooms++;
			}
		}
		
		assertEquals(6, person);
		assertEquals(6, weapons);
		assertEquals(9, rooms);
		
	}
	
	//Checks that there is a room, weapon, and person in deck
	@Test
	public void testHasCard() {
		
		Set<Card> cards = board.getCards();
		
		boolean player = false;
		boolean weapon = false;
		boolean room = false;

		for(Card card : cards) {
			if(card.getCardType() == CardType.PERSON) {
				player = true;
			}
			if(card.getCardType() == CardType.WEAPON) {
				weapon = true;
			}
			if(card.getCardType() == CardType.ROOM) {
				room = true;
			}
		}
		
		assertTrue(player);
		assertTrue(room);
		assertTrue(weapon);		
		
	}
	
	//Test that all players have roughly same number of cards after dealing
	@Test
	public void testDealingCards() { 
		//Also test that same card not given to more than 1 player
		Set<Card> cards = board.getCards();
		//board.dealCards();
		
		
	}
	
}
