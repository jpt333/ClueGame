//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	
	private static Board theInstance = new Board();
	
	private BoardCell board [][];
	
	private Map<Character, String> legend;

	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	
	private Set<BoardCell> targets;
	
	private String boardConfigFile;
	private String roomConfigFile;
	
	private Board() {}
	
	public Map<Character, String> getLegend() {
		return legend;
	}
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public static Board getInstance() {
		return theInstance;
	}
	
	public void initialize() {
		
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}

	public void setConfigFiles(String boardFile, String legendFile) {
		boardConfigFile = boardFile;
		roomConfigFile = legendFile;
	}

	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}
	
}
