package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import clueGame.Board;

class BoardTests {

<<<<<<< HEAD
	@Before
	public void beforeAll() {
		//calls in board
	}
=======
	Board board;
	
	@Before
	public void beforeAll() {
		board = new Board();
	}  
>>>>>>> branch 'master' of https://github.com/michaelberg12/ClueGame.git
	
	@Test
	void doorDirection() {
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		
		int maxRows = board.getNumRows();
		int maxCol = board.getNumColumns();
		
		for(int rows = 0; rows < maxRows; rows++) {
			for(int col = 0; col < maxCol; col++) {
				if()
			}
		}
	}

	@Test
	public void correctInitial() { //checks whether the cells have the correct initial
		assertEquals('C', board[0][0]);
		assertEquals('W', board[5][0]);
		assertEquals('B', board[1][7]);
		assertEquals('X', board[8][12]);
	}
	
	
	
}
