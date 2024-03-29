//Authors: Michael Berg and Jennifer Phan
package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;
import clueGame.Solution;

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
		board.setConfigFiles("ClueBoard.csv", "rooms.txt");
		board.setCardFiles("weapons.txt", "Person.txt");
		board.initialize();
	}
 
	//Checks correct total number of cards
	@Test
	public void testNumberOfCardsInDeck() {
	int numCards = 0;
	Set<Card> cards = board.getCards();
	//Loop and count every card in deck
	assertEquals(21, cards.size());
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
	
	//Test that all players have roughly same number of cards after dealing, test all cards dealt, test no cards dealt twice
	//@Test
	/*
	public void testDealingCards() { 
		//Also test that same card not given to more than 1 player
		Map<Player, Set<Card>> cards = board.getPlayerCards();
		Set<Player> players = board.getPlayers();
		Set<Card> deltCards = new HashSet<>();
	    board.dealCards();
	    //make sure everyone gets 3 cards
	    for(Player a1: players) {
	    	Set<Card> playerCards = cards.get(a1);
	    	assertEquals(3, playerCards.size());
	    	for(Card a2: playerCards) {
	    		assertFalse(deltCards.contains(a2));
	    		deltCards.add(a2);
	    	}
	    }
	    
	    //System.out.println(deltCards);
	    
		Solution solution = board.getSolution();
		assertFalse(deltCards.contains(solution.person));
		deltCards.add(solution.person);
		assertFalse(deltCards.contains(solution.weapon));
		deltCards.add(solution.weapon);
		
		//System.out.println(solution.room);
		
		assertFalse(deltCards.contains(solution.room));
		deltCards.add(solution.room);
		
	}
	*/
	
}
