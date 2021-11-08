public class Cell {

	protected int row;
	protected int col;
	
	/* TODO: Initialize instance variables */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/* TODO: Write the following accessor methods */
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	/* @returns true if o is a Cell and o has the same
	 * row and col as the current cell
	 * Hint: Recall that java has an instanceof keyword
	 */
	public boolean equals(Object o) {
		// make sure o is an instance of Cell
		if (o instanceof Cell) {
			// cast o into Cell and check if has same row and col as current cell
			if (((Cell) o).getRow() == this.row && ((Cell) o).getCol() == this.col) return true;
			else return false; 
		}
		else {
			return false;
		}
	}
}
