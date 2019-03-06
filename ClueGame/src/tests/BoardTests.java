package tests;

import static org.junit.Assert.assertEquals;

import experiment.BoardCell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.DoorDirection;

class BoardTests {

	Board board;
	final static int ACTUAL_DOOR_COUNT = 11;
	final static int ACTUAL_ROW_COUNT = 24;
	final static int ACTUAL_COL_COUNT = 16;
	final static int ACTUAL_ROOM_COUNT = 11;
	
	@Before
	public void beforeAll() {
		board = Board.getInstance();
		board.setConfigFiles("ClueBoard.csv", "rooms.txt");
	}  
	
	@Test
	void correctNumColRowTest() {
		//get how many rows and columns 
		int Rows = board.getNumRows();
		int Col = board.getNumColumns();
		
		assertEquals(ACTUAL_ROW_COUNT, Rows); 
		assertEquals(ACTUAL_COL_COUNT, Col); 
	}
	
	@Test
	void legendTest() {
		//tests to see if the legend loaded in correctly
		Map<Character, String> legend = board.getLegend();
		
		assertEquals("Walkway",legend.get('W'));
		assertEquals("Living room",legend.get('L'));
		assertEquals("Dining room",legend.get('D'));
		
		assertEquals(ACTUAL_ROOM_COUNT, legend.size()); 
		
	}
	
	@Test
	void doorDirectionTest() {
		//tests that there is a door in every direction
		
		//check each direction
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		
		//how many rows and columns to go through
		int maxRows = board.getNumRows();
		int maxCol = board.getNumColumns();
			
		for(int rows = 0; rows < maxRows; rows++) {
			for(int col = 0; col < maxCol; col++) {
				if(board.getCellAt(rows,col).isDoorway()) {
					if(board.getCellAt(rows,col).getDoorDirection() == DoorDirection.UP) {up = true;}
					if(board.getCellAt(rows,col).getDoorDirection() == DoorDirection.DOWN) {down = true;}
					if(board.getCellAt(rows,col).getDoorDirection() == DoorDirection.LEFT) {left = true;}
					if(board.getCellAt(rows,col).getDoorDirection() == DoorDirection.RIGHT) {right = true;}
				}
			}
		}
		assertTrue(up); 
		assertTrue(down); 
		assertTrue(left); 
		assertTrue(right); 
	}
	
	@Test
	void doorNumTest() {
		//tests that the correct number of doors are loaded and that non doors are not loaded as doors
		
		//how many rows and columns to go through
		int maxRows = board.getNumRows();
		int maxCol = board.getNumColumns();
		
		//how many
		int doorCount = 0;
		for(int rows = 0; rows < maxRows; rows++) {
			for(int col = 0; col < maxCol; col++) {
				if(board.getCellAt(rows,col).isDoorway()) {
					doorCount++;
				}
			}
		}
		assertEquals(ACTUAL_DOOR_COUNT, doorCount); 
	}

	@Test
	public void correctInitial() { //checks whether the cells have the correct initial
		assertEquals('B', board.getCellAt(0,0));
		assertEquals('W', board.getCellAt(4,8));
		assertEquals('P', board.getCellAt(0,10));
		assertEquals('X', board.getCellAt(1,22));
		assertEquals('L', board.getCellAt(6,23));
		assertEquals('K', board.getCellAt(11,22));
		assertEquals('D', board.getCellAt(16,14));
		assertEquals('C', board.getCellAt(8,5));
		assertEquals('A', board.getCellAt(13,1));
		assertEquals('G', board.getCellAt(3,14));
		assertEquals('M', board.getCellAt(12,13));
	}
	
	
	
}
