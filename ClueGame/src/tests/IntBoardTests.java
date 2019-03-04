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
	 public void testAdjacency0_0() {   //top left location
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
		 Set<BoardCell> targets = board.getTargets();
		 assertEquals(6, targets.size());
		 assertTrue(targets.contains(board.getCell(3, 0)));
		 assertTrue(targets.contains(board.getCell(2, 1)));
		 assertTrue(targets.contains(board.getCell(0, 1)));
		 assertTrue(targets.contains(board.getCell(1, 2)));
		 assertTrue(targets.contains(board.getCell(0, 3)));
		 assertTrue(targets.contains(board.getCell(1, 0)));
		}
	 
	 @Test
	 public void testAdjacency3_3() { //check bottom right corner
		 BoardCell cell = board.getCell(3,3);
		 board.calcTargets(cell, 1);
		 Set<BoardCell> targets = board.getTargets();
		 assertEquals(2, targets.size());
		 assertTrue(targets.contains(board.getCell(3,2)));
		 assertTrue(targets.contains(board.getCell(2,3)));
	 }
	 
	 @Test
	 public void testAdjacency1_3() { //check right edge
		 BoardCell cell = board.getCell(1,3);
		 board.calcTargets(cell, 1);
		 Set<BoardCell> targets = board.getTargets();
		 assertEquals(3, targets.size());
		 assertTrue(targets.contains(board.getCell(2,3)));
		 assertTrue(targets.contains(board.getCell(1,2)));
		 assertTrue(targets.contains(board.getCell(0,3)));
	 }
	 
	 @Test
	 public void testAdjacency3_0() { //check left edge
		 BoardCell cell = board.getCell(3,0);
		 board.calcTargets(cell, 2);
		 Set<BoardCell> targets = board.getTargets();
		 assertEquals(3, targets.size());
		 assertTrue(targets.contains(board.getCell(1,0)));
		 assertTrue(targets.contains(board.getCell(2,1)));
		 assertTrue(targets.contains(board.getCell(3,2)));
	 }
	 
	 @Test
	 public void testAdjacency1_1() {
		 BoardCell cell = board.getCell(1,1);
		 board.calcTargets(cell, 1);
		 Set<BoardCell> targets = board.getTargets();
		 assertEquals(4, targets.size());
		 assertTrue(targets.contains(board.getCell(1,0)));
		 assertTrue(targets.contains(board.getCell(0,1)));
		 assertTrue(targets.contains(board.getCell(2,1)));
		 assertTrue(targets.contains(board.getCell(1,2)));
	 }
	 
	 @Test
	 public void testAdjacency2_2() {
		 BoardCell cell = board.getCell(2,2);
		 board.calcTargets(cell, 1);
		 Set<BoardCell> targets = board.getTargets();
		 assertEquals(4, targets.size());
		 assertTrue(targets.contains(board.getCell(1,2)));
		 assertTrue(targets.contains(board.getCell(2,1)));
		 assertTrue(targets.contains(board.getCell(3,2)));
		 assertTrue(targets.contains(board.getCell(2,3)));
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
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
}

