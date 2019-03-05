package clueGame;

import java.util.Map;
import java.util.Set;

public class Board {
	
	
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	
	private BoardCell board [][];
	
	private Map<Character, String> legend;
	
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	
	private Set<BoardCell> targets;
	
	private String boardConfigFile;
	private String roomConfigFile;
	
	public Board getInstance() {
		return this;
	}
	
	public void initialize() {
		
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calacTargets(BoardCell cell, int pathLenght) {
		
	}
	
}
