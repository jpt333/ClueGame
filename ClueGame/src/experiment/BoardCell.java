//Authors: Michael Berg and Jennifer Phan
package experiment;

public class BoardCell {
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int row;
	public int col;
	@Override
	public String toString() {
		return "BoardCell (" + row + "," + col + ")";
	}
	
	
}
