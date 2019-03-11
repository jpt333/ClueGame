package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.DoorDirection;

public class BoardAdjTargetTests {
	private static Board board;
	
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
	
}
