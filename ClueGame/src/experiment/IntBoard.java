//Authors: Michael Berg and Jennifer Phan
package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import experiment.BoardCell;

public class IntBoard {

	private Map<BoardCell, Set<BoardCell>> adjMtx = new HashMap<BoardCell, Set<BoardCell>>(); //stores what is adjacent to a cell
	private Set<BoardCell> visited = new HashSet<>(); //stores which cells were visited
	private Set<BoardCell> targets = new HashSet<>(); //stores which cells are targets
	private BoardCell[][] grid = new BoardCell[4][4]; //Grid
	
	private static final int maxRows = 4;
	private static final int maxCol = 4;
	
	public IntBoard() { //Constructor
		for(int row = 0; row < maxRows; row ++) {
			for(int col = 0; col < maxCol; col++){
				grid[row][col] = new BoardCell(row, col);
			}
		}
		calcAdjacencies();
	}
	
	public void calcAdjacencies() {
		//calculates the adjacency list for each grid cell and stores the results as a Map in an inst. var
		// Look for neighbors. Make sure it is valid neighbor. Add neighbor
		
		for(int row = 0; row < maxRows; row ++) {
			for(int col = 0; col < maxCol; col++){
				Set<BoardCell> adjTiles = new HashSet<>();
				//if row less than board size do
				
				//bottom adj tile
				if(row > 0) {
					adjTiles.add(grid[row-1][col]);
				}
				//if col less than board size do
				//left adj tile
				if(col > 0) {
					adjTiles.add(grid[row][col-1]);
				}
				
				//right adj tile
				if(col+1 < maxCol) {
					adjTiles.add(grid[row][col+1]);
				}
				//top adj tile
				if(row+1 < maxRows) {
					adjTiles.add(grid[row+1][col]);
				}
				try {
					adjMtx.put(grid[row][col], adjTiles);
				}
				catch(NullPointerException e) {
					System.out.println("Row " + row);
					System.out.println("Col " + col + "\n");
				}
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
	
	public Set<BoardCell> getTargets(){
		return targets; 
		//Use interface and HashSet
	}

	public Set<BoardCell> getAdjList(BoardCell cell) {
		//return the sounding tiles in a set
		return adjMtx.get(cell);
	}

	public BoardCell getCell(int i, int j) {
		//returns the cell at cord(i,j)
		return grid[i][j];
	}
	
}
