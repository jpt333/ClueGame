//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import clueGame.DoorDirection;

public class BoardCell {
	@Override
	public String toString() {
		return initital + ":" + direction;
	}

	private String initital;
	private DoorDirection direction;
	
	private Point pixel;
	private Color color;
	
	public final int WIDTH = 30;
	public final int HEIGHT = 30;
	public final int SCALE = 32;
	private DoorDirection doorDir;
	
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
		//Draw rectangles and other things for board
		if(initital == "W") {
			color = Color.DARK_GRAY;
			g.setColor(color);
			g.fillRect(pixel.x, pixel.y, WIDTH, HEIGHT);
		}
		if(this.isRoom()) {
			if(isDoorway()) {
				if (doorDir == DoorDirection.UP)
				{
					color = Color.WHITE;
					g.setColor(color);
					g.fillRect(pixel.x, pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pixel.x, pixel.y, WIDTH, 5);
				}
				else if (doorDir == DoorDirection.DOWN)
				{
					
				}
				else if (doorDir == DoorDirection.LEFT)
				{
					
				}
				else if (doorDir == DoorDirection.RIGHT)
				{
					
				}
				else if(doorDir == DoorDirection.RIGHT) {
					
				}
			}
		}
	}
}
