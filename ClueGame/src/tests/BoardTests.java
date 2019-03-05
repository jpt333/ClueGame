package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import clueGame.Board;

class BoardTests {

	Board board;
	
	@Before
	public void beforeAll() {
		board = new Board();
	}  
	
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
		assertEquals('B', board.getBoard()[0][0]);
		assertEquals('W', board.getBoard()[4][8]);
		assertEquals('P', board.getBoard()[0][10]);
		assertEquals('X', board.getBoard()[1][22]);
		assertEquals('L', board.getBoard()[6][23]);
		assertEquals('K', board.getBoard()[11][22]);
		assertEquals('D', board.getBoard()[16][14]);
		assertEquals('C', board.getBoard()[8][5]);
		assertEquals('A', board.getBoard()[13][1]);
		assertEquals('G', board.getBoard()[3][14]);
		assertEquals('M', board.getBoard()[12][13]);
	}
	
	
	
}
