import java.awt.Color;
import java.util.ArrayList;
import objectdraw.*;

public class Grid extends ActiveObject {

	protected FilledRect[][] grid;
	protected FramedRect[][] borders;
	protected int box_size;
	protected boolean running;
	protected static final int XOFFSET=8;
	protected static final int YOFFSET=8;
	
	public Grid(int window_size, int box_size, DrawingCanvas canvas) {
		this.box_size = box_size;
		grid = new FilledRect[(window_size-2*YOFFSET)/box_size][(window_size-2*XOFFSET)/box_size];
		borders = new FramedRect[(window_size-2*YOFFSET)/box_size][(window_size-2*XOFFSET)/box_size];
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[0].length; col++) {
				grid[row][col] = new FilledRect(col*box_size+XOFFSET, row*box_size+YOFFSET, box_size, box_size, canvas);
				grid[row][col].setColor(Color.WHITE);
				borders[row][col] = new FramedRect(col*box_size+XOFFSET, row*box_size+YOFFSET, box_size, box_size, canvas);
			}
		}
		running = false;
    	this.start();
	}
	
	/* TODO: Update this method to return the cell in which the
	 * given point resides. 
	 */
	public Cell getCell(Location point) {
		int row = (int)(((point.getY()-YOFFSET)/box_size));
		int col = (int)(((point.getX()-XOFFSET)/box_size));
		Cell c = new Cell (row, col);
		return c;

	}
	
	/* TODO: Update this method to make a black cell white or a 
	 * white cell black.  Also return the cell that you toggled. 
	 */
	public Cell toggle(Location point) {
		Cell c = getCell(point);
		toggle(c.getRow(), c.getCol());
		return c;
	}
	
	/* TODO: Given a row and column in the grid, switch the
	 * color of the cell at that position. 
	 */
	public void toggle(int row, int col) {
		// if row/col is in the grid
		// since row/col is used as an index, it should go from 0 to 1 less than the the number of row/col
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {
			Color color = grid[row][col].getColor();
			if (color == Color.WHITE) grid[row][col].setColor(Color.BLACK);
			else if (color == Color.BLACK) grid[row][col].setColor(Color.WHITE);
		}
	}
	
	public void toggleRunning() {
		running = !running;
	}
	
	/* TODO: Return true if the cell at the given row and col is alive.
	 * NB: row and col may be values that are outside the grid. 
	 * Cells outside the grid are not alive. 
	 */
	protected boolean isAlive(int row, int col) {
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {
			// cell is alive if black, dead if white
			Color color = grid[row][col].getColor();
			if (color == Color.WHITE) return false;
			else return true;
		}
		else {
			// cell is also dead if outside of the grid
			return false;
		}
		
	}
	
	/* TODO: Return the number of alive cells that are adjacent
	 * to the given row and col. 
	 */
	protected int liveNeighbors(int row, int col) {
		int liveNeighbors = 0;
        for(int dx = -1; dx <= 1; dx ++) {
            for(int dy = -1; dy <=1; dy++) {
                if(!(dx==0 && dy==0) && isAlive(row+dy, col+dx)) liveNeighbors++;
            }
        }
        return liveNeighbors;
	}
	
	
	/* TODO: Set all of the cells in the grid to WHITE/off/dead
	 */
	public void clear() {
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c].setColor(Color.WHITE);
			}
		}
	}
	
	/* TODO: Set a given cell to BLACK/alive/on if it is within
	 * the grid. 
	 */
	private void on(int row, int col) {
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {
			grid[row][col].setColor(Color.BLACK);
		}
	}
	
	/* Mystery method.  Figure out when it gets used and why it's 
	 * interesting.  
	 */
	public void gliderGun(int row, int col) {
		// creates a glider gun
		on(row,col);
		on(row,col+1);
		on(row+1,col);
		on(row+1,col+1);
		on(row,col+10);
		on(row+1,col+10);
		on(row+2,col+10);
		on(row+3,col+11);
		on(row-1,col+11);
		on(row-2,col+12);
		on(row-2,col+13);
		on(row+4,col+12);
		on(row+4,col+13);
		on(row+1,col+14);
		on(row-1,col+15);
		on(row+3,col+15);
		on(row,col+16);
		on(row+1,col+16);
		on(row+2,col+16);
		on(row+1,col+17);
		on(row,col+20);
		on(row,col+21);
		on(row-1,col+20);
		on(row-1,col+21);
		on(row-2,col+20);
		on(row-2,col+21);
		on(row-3,col+22);
		on(row+1,col+22);
		on(row-3,col+24);
		on(row+1,col+24);
		on(row-4,col+24);
		on(row+2,col+24);
		on(row-1,col+34);
		on(row-2,col+34);
		on(row-1,col+35);
		on(row-2,col+35);

	}
	
	public void run() {
		while(true) {
			if(running) {
				// TODO:  Insert the logic to play the Game of Life
				
				// create new array list of array list of booleans to keep track of new configuration
				// can't directly change grid because all cells should change concurrently
				ArrayList<ArrayList<Boolean>> newBoard = new ArrayList<ArrayList<Boolean>>();
				for (int r = 0; r < grid.length; r++) {
					ArrayList<Boolean> newRow = new ArrayList<Boolean>();
					newBoard.add(newRow);
					for (int c = 0; c < grid[r].length; c ++) {
						int liveNeighbors = liveNeighbors(r, c);
						boolean status = false;
						// update status of boolean depending on number of live neighbors in the grid
						// if live neighbors is 3, cell will be on regardless of current status
						// if live neighbors is 2, cell will be on if already alive
						// default is false => if live neighbors is any other number, the cell will die
						if (liveNeighbors == 3 || (liveNeighbors == 2 && isAlive(r, c))) {
							status = true;
						}
						newRow.add(status);
					}
				}
				// update the grid =>
				// change cells in the grid based on value of boolean in 2-D array list
				for (int r = 0; r < grid.length; r++) {
					for (int c = 0; c < grid[r].length; c++) {
						// if the boolean value at the corresponding row and col is true:
						// change the grid cell to black/alive
						if (newBoard.get(r).get(c)) grid[r][c].setColor(Color.BLACK);
						// otherwise. if boolean is false, the cell should be white/dead
						else grid[r][c].setColor(Color.WHITE);
					}
				}
			}
			// pause for a tenth of a second
			pause(100);	
		}
	}
}
