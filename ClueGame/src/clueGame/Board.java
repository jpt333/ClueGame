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
		
		visited = new HashSet<>();
		targets = new HashSet<>();
		
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		calcAdjacencies();
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		legend= new HashMap<Character, String>();
		Scanner scanner = new Scanner(new File(roomConfigFile)); 
	    while (scanner.hasNextLine()) {
	    	String[] line = scanner.nextLine().split(", ");
	        if(line.length == 3) {
	        	//make sure rooms are 1 symbol
	        	if(line[0].length() != 1) {throw new BadConfigFormatException();}
	        	Character symbol = line[0].charAt(0);
	        	//make sure there is a string for the room name
	        	if(line[1].length() == 0) {throw new BadConfigFormatException();}
	        	legend.put(symbol,  line[1]);
	        	//make sure that it is a card or other
	        	if(!line[2].equals("Card") && !line[2].equals("Other")) {throw new BadConfigFormatException(line[2]);}
	        	
	        }else {
	        	throw new BadConfigFormatException();
	        }
	    }	
	    scanner.close();
	}
	
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		
		numRows = 0;
		numColumns = 0;
		
		Scanner scanner = new Scanner(new File(boardConfigFile));
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(",");
			//iterates through the line
			for(int a1 = 0; a1 < line.length; a1++) {
	        	//make sure correct length board cant be over 99 in size
				if(line[a1].length() > 2 || line[a1].length() == 0) {throw new BadConfigFormatException();}
				//check if number
				try {
					 Double.parseDouble(line[a1]); 
					 //counts the number of numbers
					 
					 //if it is a  numbers
					 if(a1 != 0) {
						 numColumns--;
					 }else{
						 numRows--;
					 }
					 break;
				}catch(NumberFormatException e) {
					//not a number
					
					//make sure correct  number of characters
					if(line[a1].length() > 2 || line[a1].length() == 0){throw new BadConfigFormatException();}
					//check that the room exists
					if(!legend.containsKey(line[a1].charAt(0))){throw new BadConfigFormatException();}
					//doorways
		        	if(line[a1].length() == 2) {
		        		if(line[a1].endsWith("U")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.UP);
		        		}
		        		else if(line[a1].endsWith("D")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.DOWN);
		        		}
		        		else if(line[a1].endsWith("L")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.LEFT);
		        		}
		        		else if(line[a1].endsWith("R")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.RIGHT);
		        		}
		        		else if(line[a1].endsWith("N")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.NONE);
		        		}
		        		else {
		        			throw new BadConfigFormatException();
		        		}
		        	}
		        	else {
		        		//everything else
		        		board[numRows][a1] = new BoardCell(line[a1]);
		        	}
		        	//always take the max length
		        	if(a1 == 0 && line.length > numColumns) {
		        		numColumns = line.length;
					}
		        	//check that all rows are the same length
		        	if(line.length < numColumns) {throw new BadConfigFormatException();}
		        }
			}
			numRows++;
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
				
				
				if(board[row][col].getDoorDirection() == DoorDirection.DOWN 
						&& row+1 < numRows
						&& board[row + 1][col].getInitial() == 'W'){
					adjTiles.add(board[row+1][col]);
				}
				
				if(board[row][col].getDoorDirection() == DoorDirection.UP 
						&& row > 0
						&& board[row - 1][col].getInitial() == 'W'){
					adjTiles.add(board[row - 1][col]);
				}
				
				if(board[row][col].getDoorDirection() == DoorDirection.LEFT 
						&& col > 0
						&& board[row][col - 1].getInitial() == 'W'){
					adjTiles.add(board[row][col - 1]);
				}
				
				if(board[row][col].getDoorDirection() == DoorDirection.RIGHT 
						&& col+1 < numColumns
						&& board[row][col + 1].getInitial() == 'W'){
					adjTiles.add(board[row][col + 1]);
				}
				
				//bottom adj tile
				if(board[row][col].getInitial() == 'W'){
					if(row > 0){
						if(board[row][col].getInitial() == board[row-1][col].getInitial() //same room or walkway
							||(board[row][col].getInitial() != board[row-1][col].getInitial() //not the same room
							&& (board[row - 1][col].getDoorDirection() == DoorDirection.DOWN //can move down
							|| board[row - 1][col].getDoorDirection() == DoorDirection.NONE))) { //can move down
		
							adjTiles.add(board[row-1][col]);
							
						}
					}
					//if col less than board size do
					//left adj tile
					if(col > 0){
						if(board[row][col].getInitial() == board[row][col - 1].getInitial() //same room or walkway
							||(board[row][col].getInitial() != board[row][col - 1].getInitial() //not the same room
							&& (board[row][col - 1].getDoorDirection() == DoorDirection.RIGHT //can move down
							|| board[row][col - 1].getDoorDirection() == DoorDirection.NONE))) {
						
							adjTiles.add(board[row][col-1]);
							
						}
					}
					
					//right adj tile
					if(col+1 < numColumns){
						if(board[row][col].getInitial() == board[row][col + 1].getInitial() //same room or walkway
							||(board[row][col].getInitial() != board[row][col + 1].getInitial() //not the same room
							&& (board[row][col + 1].getDoorDirection() == DoorDirection.LEFT //can move down
							|| board[row][col + 1].getDoorDirection() == DoorDirection.NONE))) {
						
							adjTiles.add(board[row][col+1]);
							
						}
					}
					//top adj tile
					if(row+1 < numRows){
						if(board[row][col].getInitial() == board[row + 1][col].getInitial() //same room or walkway
							||(board[row][col].getInitial() != board[row + 1][col].getInitial() //not the same room
							&& (board[row + 1][col].getDoorDirection() == DoorDirection.UP //can move down
							|| board[row + 1][col].getDoorDirection() == DoorDirection.NONE))) {
						
							adjTiles.add(board[row+1][col]);
						}
						
					}
				}
				adjMatrix.put(board[row][col], adjTiles);
			}
		}
	}
	
	public void calcTargetsFun(BoardCell startCell, int pathLength) {
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
			if(pathLength == 1 || (startCell.getInitial() != cell.getInitial() && startCell.getInitial() == 'W')) {
				targets.add(cell);
			}else {
				calcTargetsFun(cell, pathLength-1); //recursively calls
			}
			visited.remove(cell);
		}
		
	}
	
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited.clear();
		targets.clear();
		calcTargetsFun(startCell, pathLength);
	}
	
	public void calcTargets(int i, int j, int k) {
		calcTargets(board[i][j], k);
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

	public Set<BoardCell> getTargets() {
		return targets; 
	}
	
}
