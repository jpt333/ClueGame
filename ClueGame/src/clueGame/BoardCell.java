//Authors: Michael Berg and Jennifer Phan
package clueGame;

import clueGame.DoorDirection;

public class BoardCell {
	private int row;
	private int column;
	private char initital;
	private DoorDirection direction;
	
	public BoardCell(int row, int column, char initital, DoorDirection direction) {
		this.row = row;
		this.column = column;
		this.initital = initital;
		this.direction = direction;
	}

	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}

	public char getInitial() {
		return initital;
	}
	
	public DoorDirection getDoorDirection() {
		return direction;
	}
}
