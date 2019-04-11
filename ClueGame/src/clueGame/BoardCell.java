//Authors: Michael Berg and Jennifer Phan
package clueGame;

import clueGame.DoorDirection;

public class BoardCell {
	@Override
	public String toString() {
		return initital + ":" + direction;
	}

	private String initital;
	private DoorDirection direction;
	
	public BoardCell(String initital, DoorDirection direction) {
		this.initital = initital;
		this.direction = direction;
	}

	public BoardCell(String initital) {
		this.initital = initital;
		this.direction = null;
	}

	public Boolean isWalkway() {
		if(initital == "W"){
			return true;
		}
		return false;
	}
	
	public Boolean isRoom() {
		if(initital != "W"){
			return true;
		}
		return false;
	}
	
	public Boolean isDoorway() {
		if(direction ==  null || direction == DoorDirection.NONE){
			return false; 
		}
			
		return true;
	}

	public char getInitial() {
		return initital.charAt(0);
	}
	
	public DoorDirection getDoorDirection() {
		return direction;
	}
	
	public void draw(Graphics g) {
		//Draw rectangles and other things for board.
	}
}
