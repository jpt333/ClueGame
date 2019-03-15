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

import clueGame.BoardCell;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	
	private static Board theInstance;
	
	private BoardCell board [][];
	
	private Map<Character, String> legend; //stores what is in legend

	private Map<BoardCell, Set<BoardCell>> adjMatrix; //stores what is adjacent to a cell
	
	private Set<BoardCell> visited; //stores which cells were visited
	private Set<BoardCell> targets; //stores which cells are targets
	
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
		if(theInstance == null) {
			theInstance = new Board();
		}
		return theInstance;
	}
	
	public void initialize(){
		//initialize variables
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		legend= new HashMap<Character, String>();
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		visited = new HashSet<>();
		targets = new HashSet<>();
		
		int currentRow = 0;
		int currentCol = 0;
		try {
			loadRoomConfig();
			Scanner scanner = new Scanner(new File(boardConfigFile));
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
	    		Scanner rowScanner = new Scanner(nextLine);
				rowScanner.useDelimiter(",");
		        while (rowScanner.hasNext()) {
		        	if(rowScanner.hasNextInt()) {
		        		break;
		        	}
		        	String item = rowScanner.next();
		
		        	//doorways
		        	if(item.length() == 2) {
		        		if(item.endsWith("U")) {
		        			board[currentRow][currentCol] = new BoardCell(item, DoorDirection.UP);
		        		}
		        		if(item.endsWith("D")) {
		        			board[currentRow][currentCol] = new BoardCell(item, DoorDirection.DOWN);
		        		}
		        		if(item.endsWith("L")) {
		        			board[currentRow][currentCol] = new BoardCell(item, DoorDirection.LEFT);
		        		}
		        		if(item.endsWith("R")) {
		        			board[currentRow][currentCol] = new BoardCell(item, DoorDirection.RIGHT);
		        		}
		        		if(item.endsWith("N")) {
		        			board[currentRow][currentCol] = new BoardCell(item, DoorDirection.NONE);
		        		}
		        	}
		        	else {
		        		board[currentRow][currentCol] = new BoardCell(item, DoorDirection.NONE);
		        	}
		            currentCol++;
		        }
		        currentRow++;
		        if(currentCol > numColumns) {numColumns = currentCol;}
		        if(currentCol == 0) {
		        	currentRow--;
				}
		        currentCol = 0;
		        rowScanner.close();
		    }
			numRows = currentRow;
			
			
		    scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} 
		calcAdjacencies();
	}
	
	public void loadRoomConfig() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(roomConfigFile)); 
	    while (scanner.hasNextLine()) {
    		Scanner rowScanner = new Scanner(scanner.nextLine());
			rowScanner.useDelimiter(", ");
	        if(rowScanner.hasNext()) {
	        	Character symbol = rowScanner.next().charAt(0);
	        	legend.put(symbol,  rowScanner.next());
	        }
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
		
		Set<BoardCell> adjTiles = adjMatrix.get(startCell);
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

	public Set<BoardCell> getAdjList(int i, int j) {
		return adjMatrix.get(board[i][j]);
	}
	
}
