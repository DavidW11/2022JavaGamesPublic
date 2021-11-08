/* Thought Questions:

 1.
Pressing the 'g' key generates a certain board configuration that is referred to as a glider gun.
The configuration includes 2 stable 2 by 2 squares on each side, with a group of blocks that rebounds between 
these 2 squares.
The interesting thing about the glider gun is that every time the two groups of blocks connect in the middle,
a new group of blocks is created that travels indefinitely towards the bottom right direction, called a glider.
In other words, each revolution of the glider gun produces a new glider.

John Conway, the creator of the Game of Life, originally conjectured that no pattern can grow without limit.
In other words, any initial pattern with a finite number of living cells cannot grow beyond some finite upper limit.
The glider gun directly disproves this conjecture.

 

 2.
The fact that Life is turn based means that all cells must change at the same time. 
We cannot change the cells as we iterate through the grid, because the updated cells 
will then affect the status of following cells. 
This impacts the memory needed because 
a new array must be created to temporarily hold the updated values of each cell, 
which is then copied over to the actual grid once all the cells are checked to be alive or dead. 
This temporary array must be updated after every ‘turn’.


 
 3.
Making cell responsible for drawing FilledRect and Framed Rect would replace the grid array 
of rectangles with a collection of individual rectangles. 
While this would make interacting with individual cells more straightforward, 
it would make manipulating the array of cells more complicated than it needs to be. 
In order to iterate through the cells, a 2-D array that holds Cell objects 
would have to be created in order to iterate through the cells, 
which is necessary for liveNeighbors and the game logic. 
Creating the rectangles in the cell class and then adding it to another array of cell object 
is redundant, compared to simply creating an array of rectangles as we did in the grid class.



 4.
Supporting a board that expends infinitely in all sides, can be done in a couple ways. 
One way is to manually extend the dimensions of the board when needed. 
When a block on the outer edge of the board becomes alive, 
the board’s length in that dimension should be increased by a factor of 2, 
very similar to how a vector is extended. 
A temporary 2-D array with double the length in that dimension is created, 
all of the original values are copied over, and the original array is set to the temporary array.
The other way is to create an array list of array list of Framed Rectangles, 
which will automatically extend the board size when needed.
protected ArrayList<ArrayList<FilledRect>> grid2;


 
 */


import java.awt.Color;
import java.awt.Container;
import javax.swing.JRootPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import objectdraw.*;
public class GameOfLife extends WindowController implements KeyListener {
	
	protected static final int WINDOW_SIZE = 616;
	protected static final int BOX_SIZE = 15;
	protected Cell lastToggledCell;
	
	protected Grid grid;
	
	public void begin() {
		int yoffset = 0;
		
		/* The coordinate system of the grid is thrown off slightly by
		 * the existence of the system menu bar.  The code below figures out
		 * the height of the menu bar. The call to resize at the end of this
		 * method takes this offset into account when making the whole grid
		 * visible. 
		 */
		Container c = this;
		while(! (c instanceof JRootPane)) {
			yoffset += (int)(c.getParent().getY());
			c = c.getParent();
		}
		grid = new Grid(WINDOW_SIZE, BOX_SIZE, canvas);
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        lastToggledCell = null;
        resize(WINDOW_SIZE, WINDOW_SIZE + yoffset);
	}
	
	public void onMousePress(Location point) {
		/* TODO: Toggle the cell that was clicked on
		 * and keep track of what cell you just 
		 * changed. 
		 */
		
		// toggle point and set lastToggledCell to the last toggled cell
		lastToggledCell = grid.toggle(point);
	}
	
	public void onMouseDrag(Location point) {
		/* TODO: Toggle the cell under the mouse if
		 * it wasn't the last cell to be toggled. 
		 */
		
		// if the point is not in the same cell that was last toggled =>
		// toggle the cell
		// otherwise, the cell what toggle back and forth super fast
		if (!lastToggledCell.equals(grid.getCell(point))) {
			// update lastToggledCell and toggle cell
			lastToggledCell = grid.toggle(point);
		}
	}
	
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	char letter = e.getKeyChar();
    	if(letter == 'g' && lastToggledCell != null) {
    		grid.gliderGun(lastToggledCell.getRow(), lastToggledCell.getCol());
    	} else if (letter == 'c') {
    		/* TODO: Clear the grid */
			grid.clear();
    	}
    	else {
    		/* TODO: Toggle whether the grid is running */
			grid.toggleRunning();
    	}
    }
	
    public static void main(String[] args) { 
        new GameOfLife().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
}
