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
	
	public void calcAdjacencies() {
		//calculates the adjacency list for each grid cell and stores the results as a Map in an inst. var
		// Look for neighbors. Make sure it is valid neighbor. Add neighbor
		
		Set<BoardCell> adjTiles;
		adjTiles.add(grid[x-1][y]);
		adjTiles.add(grid[x+1][y]);
		adjTiles.add(grid[x][y-1]);
		adjTiles.add(grid[x][y+1]);
		
		adjMtx.put(grid[x][y], adjTiles);
		
	}
	public Set<BoardCell> getAdjList() {
		//Returns the adjacency list for one cell
		return null;
	}
	
	public void calcTargets(int startCell, int pathLength) {
		//Calculates targets that are pathLength distance 
		//from start cell. List of targets stored as a set in inst. var.
	}
	
	public Set<BoardCell> getTargets(){
		//returns list of targets as a set
		return targets; //returns type Set<BoardCell>
		//Use interface and HashSet
	}
}
