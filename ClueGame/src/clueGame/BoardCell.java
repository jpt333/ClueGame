//Authors: Michael Berg and Jennifer Phan
package clueGame;

import clueGame.DoorDirection;

public class BoardCell {
	@Override
	public String toString() {
		return "BoardCell [initital=" + initital + ", direction=" + direction + "]";
	}

	private String initital;
	private DoorDirection direction;
	
	public BoardCell(String initital, DoorDirection direction) {
		this.initital = initital;
		this.direction = direction;
	}

	public BoardCell(String initital) {
		this.initital = initital;
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
		if(direction ==  null){
			return true;
		}
		return false; 
	}

	public char getInitial() {
		return initital.charAt(0);
	}
	
	public DoorDirection getDoorDirection() {
		return direction;
	}
}
