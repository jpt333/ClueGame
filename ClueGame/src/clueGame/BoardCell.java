//Authors: Michael Berg and Jennifer Phan
package clueGame;

import clueGame.DoorDirection;

public class BoardCell {
	private int row;
	private int column;
	private String initital;
	private DoorDirection direction;
	
	public BoardCell(int row, int column, String initital, DoorDirection direction) {
		this.row = row;
		this.column = column;
		this.initital = initital;
		this.direction = direction;
	}

	public boolean isWalkway() {
		if(initital == "W"){
			return true;
		}
		return false;
	}
	
	public boolean isRoom() {
		if(initital != "W"){
			return true;
		}
		return false;
	}
	
	public boolean isDoorway() {
		if(initital.length() == 2){
			return true;
		}
		return false;
	}

	public char getInitial() {
		return initital;
	}
	
	public DoorDirection getDoorDirection() {
		return direction;
	}
}
