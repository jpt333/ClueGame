package tests;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class BoardAdjTargetTests {
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		board.setConfigFiles("ClueBoard.csv", "rooms.txt");
		// set the file names to use my config files
		board.initialize();
	}
	
	@Test
	public void testAdjacenciesInsideRooms(){
		//Test corner
		Set<BoardCell> testList = board.getAdjList(0,0);
		assertEquals(0, testList.size());
		//Test with walkway beneath
		testList = board.getAdjList(8,0);
		assertEquals(0, testList.size());
		//Test one with walkway above
		testList = board.getAdjList(10,0);
		assertEquals(0, testList.size());
		//Test one in middle of a room
		testList = board.getAdjList(5,8);
		assertEquals(0, testList.size());
		//Test next to door
		testList = board.getAdjList(21,7);
		assertEquals(0, testList.size());
		//Test in corner of room
		testList = board.getAdjList(12,3);
		assertEquals(0, testList.size());
	}
	
	@Test
	public void testAdjacencyRoomExit() {
		//Test Doorway RIGHT
		Set<BoardCell> testList = board.getAdjList(8,2);
		assertEquals(1, testList.size());
		//Test Doorway LEFT
		testList = board.getAdjList(7,6);
		assertEquals(1, testList.size());
		//Test Doorway Down
		testList = board.getAdjList(22,6);
		assertEquals(1, testList.size());
		//Test Doorway Up
		testList = board.getAdjList(12,5);
		assertEquals(1, testList.size());
		
	}
	@Test
	public void testAdjacencyDoorways() {
		//Test beside door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(11,1);
		assertTrue(testList.contains(board.getCellAt(11,0)));
		assertTrue(testList.contains(board.getCellAt(11,2)));
		assertTrue(testList.contains(board.getCellAt(12,1)));
		assertTrue(testList.contains(board.getCellAt(10,1)));
		assertEquals(4, testList.size());
		//Test beside door DOWN
		testList = board.getAdjList(3,14);
		assertTrue(testList.contains(board.getCellAt(2,14)));
		assertTrue(testList.contains(board.getCellAt(4,14)));
		assertTrue(testList.contains(board.getCellAt(3,13)));
		assertTrue(testList.contains(board.getCellAt(3,15)));
		assertEquals(4, testList.size());
		//Test beside door LEFT
		testList = board.getAdjList(16,11);
		assertTrue(testList.contains(board.getCellAt(16,12)));
		assertTrue(testList.contains(board.getCellAt(16,10)));
		assertTrue(testList.contains(board.getCellAt(15,11)));
		assertEquals(3, testList.size());
		//Test beside door UP
		testList = board.getAdjList(8,16);
		assertTrue(testList.contains(board.getCellAt(9,16)));
		assertTrue(testList.contains(board.getCellAt(7,16)));
		assertTrue(testList.contains(board.getCellAt(8,15)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacencyWalkways(){
		//Test next to room w/ 3 walkway pieces
		Set<BoardCell> testList = board.getAdjList(5,3);
		assertTrue(testList.contains(board.getCellAt(4,3)));
		assertTrue(testList.contains(board.getCellAt(6,3)));
		assertTrue(testList.contains(board.getCellAt(5,4)));
		assertEquals(3, testList.size());
		//Test on left edge with 1 walkway piece
		testList = board.getAdjList(9,0);
		assertTrue(testList.contains(board.getCellAt(9,1)));
		assertEquals(1, testList.size());
		//Test at top edge with left and right
		testList = board.getAdjList(0,8);
		assertTrue(testList.contains(board.getCellAt(0,7)));
		assertTrue(testList.contains(board.getCellAt(0,9)));
		assertEquals(2, testList.size());
		//Test with 4 walkways
		testList = board.getAdjList(17,4);
		assertTrue(testList.contains(board.getCellAt(16,4)));
		assertTrue(testList.contains(board.getCellAt(18,4)));
		assertTrue(testList.contains(board.getCellAt(17,3)));
		assertTrue(testList.contains(board.getCellAt(17,5)));
		assertEquals(4, testList.size());

	}
	
	@Test
	public void testTargetsIntoRoom() {
		//test at distance 2
		board.calcTargets(8, 15, 2);
		Set<BoardCell> targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(9, 16)));
		//directly up/down
		assertTrue(targets.contains(board.getCellAt(6, 15)));
		assertTrue(targets.contains(board.getCellAt(10, 15)));
		//one right one down/up
		assertTrue(targets.contains(board.getCellAt(9, 14)));
		assertTrue(targets.contains(board.getCellAt(7, 14)));
		//one up, one right
		assertTrue(targets.contains(board.getCellAt(7, 16)));
		
	}
	
	@Test
	public void testRoomExit() {
		board.calcTargets(22, 10, 1);
		Set<BoardCell> targets = board.getTargets();
		
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(22, 9)));
		
		board.calcTargets(16, 12, 2);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 10)));
		assertTrue(targets.contains(board.getCellAt(15, 11)));
	}
	
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(13, 2, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(14, 2)));
		assertTrue(targets.contains(board.getCellAt(13, 1)));
		assertTrue(targets.contains(board.getCellAt(12, 2)));
		
		board.calcTargets(0, 3, 1);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 3)));
		assertTrue(targets.contains(board.getCellAt(0, 4)));
	}
	
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(0, 12, 2);
		Set<BoardCell> targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(0, 10)));
		assertTrue(targets.contains(board.getCellAt(2, 12)));
		assertTrue(targets.contains(board.getCellAt(1,11)));
		
		board.calcTargets(18, 15, 2);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(20, 15)));
		assertTrue(targets.contains(board.getCellAt(19, 14)));	
		assertTrue(targets.contains(board.getCellAt(16, 15)));
		assertTrue(targets.contains(board.getCellAt(17, 14)));
	}
	
	@Test
	public void testTargetsSevenSteps() {
		board.calcTargets(24, 14, 7);
		Set<BoardCell> targets = baord.getTargets();
		assertTrue(targets.contains(board.getCellAt(17, 14)));
		assertTrue(targets.contains(board.getCellAt(18, 15)));
		assertTrue(targets.contains(board.getCellAt(20, 13)));
		assertTrue(targets.contains(board.getCellAt(20, 11)));
		assertTrue(targets.contains(board.getCellAt(19, 12)));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
