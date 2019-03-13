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
		Set<BoardCell> testList = board.getAdjList()
	}
}
