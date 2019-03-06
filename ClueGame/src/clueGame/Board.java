//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import experiment.BoardCell;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	
	private static Board theInstance = new Board();
	
	private BoardCell board [][] = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	
	private Map<Character, String> legend= new HashMap<Character, String>(); //stores what is in legend

	private Map<BoardCell, Set<BoardCell>> adjMatrix = new HashMap<BoardCell, Set<BoardCell>>(); //stores what is adjacent to a cell
	
	private Set<BoardCell> visited = new HashSet<>(); //stores which cells were visited
	private Set<BoardCell> targets = new HashSet<>(); //stores which cells are targets
	
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
	
	public void initialize() throws FileNotFoundException{
		List<List<String>> boardArray = new ArrayList<>();
		Scanner scanner = new Scanner(new File(boardConfigFile)); 
	    while (scanner.hasNextLine()) {
    		List<String> values = new ArrayList<String>();
    		Scanner rowScanner = new Scanner(scanner.nextLine());
			rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	        boardArray.add(values);
	        rowScanner.close();
	    }	
	    scanner.close();
	}
	
	public void loadRoomConfig() throws FileNotFoundException {
		List<List<String>> boardArray = new ArrayList<>();
		Scanner scanner = new Scanner(new File(roomConfigFile)); 
	    while (scanner.hasNextLine()) {
    		List<String> values = new ArrayList<String>();
    		Scanner rowScanner = new Scanner(scanner.nextLine());
			rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	        boardArray.add(values);
	        rowScanner.close();
	    }	
	    scanner.close();
	}
	
	public void calcAdjacencies() {
		//calculates the adjacency list for each grid cell and stores the results as a Map in an inst. var
		// Look for neighbors. Make sure it is valid neighbor. Add neighbor
		
		for(int row = 0; row < numRows; row ++) {
			for(int col = 0; col < numColumns; col++){
				Set<BoardCell> adjTiles = new HashSet<>();
				//if row less than board size do
				
				//bottom adj tile
				if(row > 0) {
					adjTiles.add(board[row-1][col]);
				}
				//if col less than board size do
				//left adj tile
				if(col > 0) {
					adjTiles.add(board[row][col-1]);
				}
				
				//right adj tile
				if(col+1 < numColumns) {
					adjTiles.add(board[row][col+1]);
				}
				//top adj tile
				if(row+1 < numRows) {
					adjTiles.add(board[row+1][col]);
				}
				adjMatrix.put(board[row][col], adjTiles);
			}
		}
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		//Calculates targets that are pathLength distance 
		//from start cell. List of targets stored as a set in inst. var.
		
		visited.add(startCell);
		
		Set<BoardCell> adjTiles = adjMtx.get(startCell);
		for(BoardCell cell : adjTiles) {
			//if already in visited list, skip the rest
			if(visited.contains(cell)) {
				continue;
			}else {
				visited.add(cell); //adds cell into visited list
			}  
			if(pathLength == 1) {
				targets.add(cell);
			}else {
				calcTargets(cell, pathLength-1); //recursively calls
			}
			visited.remove(cell);
		}
		
	}

	public void setConfigFiles(String boardFile, String legendFile) {
		boardConfigFile = boardFile;
		roomConfigFile = legendFile;
	}

	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}
	
}
