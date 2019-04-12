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
	
	private Point pix;
	private Color color;
	
	public final int WIDTH = 29;
	public final int HEIGHT = 26;
	
	public BoardCell(String initital, DoorDirection direction, int row, int col) {
		pix = new Point();
		
		this.row = row;
		this.col = col;
		this.initital = initital; 
		this.direction = direction;
		pix.x = col * WIDTH;
		pix.y = row * HEIGHT;
	}

	public BoardCell(String initital, int row, int col) {
		pix = new Point();
		
		this.row = row;
		this.col = col;
		this.initital = initital;
		this.direction = null;
		
		pix.x = col * WIDTH;
		pix.y = row * HEIGHT;
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

	public Point getCord() {
		return pix;
	}
	
	public char getInitial() {
		return initital.charAt(0);
	}
	
	public DoorDirection getDoorDirection() {
		return direction;
	}
	
	public void draw(Graphics g) {
		//Draw rectangles and other things for board
<<<<<<< HEAD
		
		
		if(isWalkway()) {
			color = Color.DARK_GRAY;
=======
		if(this.isWalkway()) {
			color = Color.YELLOW;
>>>>>>> branch 'master' of https://github.com/michaelberg12/ClueGame.git
			g.setColor(color);
			g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
		}
<<<<<<< HEAD
		if(isRoom()) {
=======
		if(this.isRoom()) {
			color = Color.MAGENTA;
			g.setColor(color);
			g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
		}
		
		if(this.isRoom()) {
>>>>>>> branch 'master' of https://github.com/michaelberg12/ClueGame.git
			//Paints the doorways and rooms
			if(isDoorway()) {
				if (direction == DoorDirection.UP){
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pix.x, pix.y, WIDTH, 6);
				}
				else if (direction == DoorDirection.DOWN){
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pix.x, pix.y + 20, WIDTH, 6);
				}
				else if (direction == DoorDirection.LEFT){
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pix.x, pix.y, 6, HEIGHT);
				}
				else if (direction == DoorDirection.RIGHT){
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pix.x + 23, pix.y, 6, HEIGHT);
				}
				else if(direction == DoorDirection.RIGHT) {
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
					g.setColor(Color.BLUE);
					g.fillRect(pix.x + 20, pix.y, 5, HEIGHT);
				}
				else {
					color = Color.MAGENTA;
					g.setColor(color);
					g.fillRect(pix.x, pix.y, WIDTH, HEIGHT);
				}

			}
			
		}
	} 
}
