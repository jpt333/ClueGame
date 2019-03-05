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

}
