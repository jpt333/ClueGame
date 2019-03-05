package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.DoorDirection;
import experiment.BoardCell;

class BoardTests {

	Board board;
	final static int ACTUAL_DOOR_COUNT = 11;
	final static int ACTUAL_ROW_COUNT = 24;
	final static int ACTUAL_COL_COUNT = 16;
	
	@Before
	public void beforeAll() {
		board = new Board();
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
				if(board.getBoard()[rows][col].isDoorway()) {
					if(board.getBoard()[rows][col].getDirection() == DoorDirection.UP) {up = true;}
					if(board.getBoard()[rows][col].getDirection() == DoorDirection.DOWN) {down = true;}
					if(board.getBoard()[rows][col].getDirection() == DoorDirection.LEFT) {left = true;}
					if(board.getBoard()[rows][col].getDirection() == DoorDirection.RIGHT) {right = true;}
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
				if(board.getBoard()[rows][col].isDoorway()) {
					doorCount++;
				}
			}
		}
		assertEquals(ACTUAL_DOOR_COUNT, doorCount); 
	}

}
