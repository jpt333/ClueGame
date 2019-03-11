//Authors: Michael Berg and Jennifer Phan
package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.DoorDirection;

public class BoardTests {

	private static Board board;
	
	final static int ACTUAL_DOOR_COUNT = 11;
	final static int ACTUAL_ROW_COUNT = 25;
	final static int ACTUAL_COL_COUNT = 17;
	final static int ACTUAL_ROOM_COUNT = 11;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		board.setConfigFiles("ClueBoard.csv", "rooms.txt");
		// set the file names to use my config files
		board.setConfigFiles("ClueBoard.csv", "rooms.txt");	
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	@Test
	public void correctNumColRowTest() {
		//get how many rows and columns 
		
		int Rows = board.getNumRows();
		int Col = board.getNumColumns();
		
		assertEquals(ACTUAL_ROW_COUNT, Rows); 
		assertEquals(ACTUAL_COL_COUNT, Col); 
	}
	
	@Test
	public void legendTest() {
		//tests to see if the legend loaded in correctly
		Map<Character, String> legend = board.getLegend();
		
		System.out.println(legend);
		
		assertEquals("Walkway",legend.get('W'));
		assertEquals("Living room",legend.get('L'));
		assertEquals("Dining room",legend.get('D'));
		
		assertEquals(ACTUAL_ROOM_COUNT, legend.size()); 
		
	}
	
	@Test
	public void doorDirectionTest() {
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
	public void doorNumTest() {
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
		assertEquals('B', board.getCellAt(0,0).getInitial());
		assertEquals('C', board.getCellAt(6,8).getInitial());
		assertEquals('W', board.getCellAt(0,10).getInitial());
		assertEquals('X', board.getCellAt(22,1).getInitial());
		assertEquals('L', board.getCellAt(23,6).getInitial());
		assertEquals('K', board.getCellAt(22,11).getInitial());
		assertEquals('D', board.getCellAt(14,16).getInitial());
		assertEquals('C', board.getCellAt(5,8).getInitial());
		assertEquals('A', board.getCellAt(1,13).getInitial());
		assertEquals('G', board.getCellAt(14,3).getInitial());
		assertEquals('M', board.getCellAt(13,12).getInitial());
	}
	
	
	
}
