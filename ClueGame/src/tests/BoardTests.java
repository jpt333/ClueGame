package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTests {

	@Before
	public void beforeAll() {
		//calls in board
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	public void correctInitial() { //checks whether the cells have the correct initial
		assertEquals('C', board[0][0]);
		assertEquals('W', board[5][0]);
		assertEquals('B', board[1][7]);
		assertEquals('X', board[8][12]);
	}
	
	
	
}
