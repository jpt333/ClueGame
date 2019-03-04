//Authors: Michael Berg and Jennifer Phan
package tests;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {
	
	@Before
	public void beforeAll() {
		board = new IntBoard();
	}
	 @Test
	 public void testAdjacency0() {
		 BoardCell cell = board.getCell(0,0);
		 Set<BoardCell> testList cell = board.getAdjList(cell);
		 assertTrue(testList.contains(board.getCell(1, 0)));
		 assertTrue(testList.contains(board.getCell(0, 1)));
		 assertEquals(2, testList.size());
	 }
	
}
