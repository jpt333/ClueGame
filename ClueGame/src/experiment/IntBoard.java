//Authors: Michael Berg and Jennifer Phan
package experiment;

import java.util.Map;
import java.util.Set;

import clueGame.BoardCell;

public class IntBoard {

	private Map<BoardCell, Set<BoardCell>> adjMtx; //stores what is adjacent to a cell
	private Set<BoardCell> visited; //stores which cells were visited
	private Set<BoardCell> targets; //stores which cells are targets
	private BoardCell[][] grid; //Grid
	
	public IntBoard() { //Constructor
		super();
		calcAdjacencies();
	}
	
	public void calcAdjacencies(int row, int col) {
		//calculates the adjacency list for each grid cell and stores the results as a Map in an inst. var
		// Look for neighbors. Make sure it is valid neighbor. Add neighbor
		
		Set<BoardCell> adjTiles;
		//if row less than board size do
		//bottom adj tile
		if(row > 0) {
			if(grid[row - 1][col].isWalkway()) {
				adjTiles.add(grid[row-1]col]);
			}
		}
		//if col less than board size do
		//left adj tile
		if(col > 0) {
			if(grid[row][col -1].isWalkway()) {
				adjTiles.add(grid[row][col-1]);
			}
		}
		
		//right adj tile
		if(grid[row][col+1].isWalkway()) {
			adjTiles.add(grid[row][col+1]);
		}
		//top adj tile
		if(grid[row+1][col].isWalkway()) {
			adjTiles.add(grid[row][col+1]);
		}
		adjMtx.put(grid[row][col], adjTiles);
	}
	public Set<BoardCell> getAdjList() {
		//Returns the adjacency list for one cell
		//return adjMtx;
		return null;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		//Calculates targets that are pathLength distance 
		//from start cell. List of targets stored as a set in inst. var.
		//
	}
	
	public Set<BoardCell> getTargets(){
		//returns list of targets as a set
		return targets; //returns type Set<BoardCell>
		//Use interface and HashSet
	}
}
