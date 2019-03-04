//Authors: Michael Berg and Jennifer Phan
package tests;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.Set;


import clueGame.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {
	
	IntBoard board;
	
	@Before
	public void beforeAll() {
		board = new IntBoard();
	}
	
	@Test
	public void testAdjacency0() {
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency0_3() {
		BoardCell cell = board.getCell(0,0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
}
