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

	private int row;
	private int col;
	
	private String initital;
	private DoorDirection direction;
	
	private Point pixel;
	private Color color;
	
	public final int WIDTH = 30;
	public final int HEIGHT = 30;
	public final int SCALE = 32;
	
	public BoardCell(String initital, DoorDirection direction, int row, int col) {
		this.row = row;
		this.col = col;
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
			//Paints the doorways
			if(isDoorway()) {
				if (direction == DoorDirection.UP){
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pixel.x, pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pixel.x, pixel.y, WIDTH, 6);
				}
				else if (direction == DoorDirection.DOWN){
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pixel.x, pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pixel.x, pixel.y + 20, WIDTH, 6);
				}
				else if (direction == DoorDirection.LEFT){
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pixel.x, pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pixel.x, pixel.y, 6, HEIGHT);
				}else if (direction == DoorDirection.RIGHT)
				{
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pixel.x, pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pixel.x + 20, pixel.y, 6, HEIGHT);
				}else if(direction == DoorDirection.RIGHT) {
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pixel.x, pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pixel.x + 20, pixel.y, 5, HEIGHT);
				}
			}
		}
	} 
}
