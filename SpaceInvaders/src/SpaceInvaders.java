/* Classes:
 * 
 * SpaceShip
 * 
 * Invader (1 invader)
 * 
 * Pack (2-D array of invaders, active object)
 * 
 * Laser (like colonizers, does it overlap with the target)
 * 
 * Shootable Interface (used by both invaders and spaceship, parameter for shooting at space ship or invader)
 * 
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

public class SpaceInvaders extends WindowController implements KeyListener {


	// Constants for the window
	private static final int HEIGHT=800;
	private static final int WIDTH = 800;
	
	// Constants for the space ship. 
	// Move into a dedicated class?
	private static final int SHIP_WIDTH = 50;
	private static final int SHIP_HEIGHT = 50;
	Mothership ship;
	Fleet f;

	// remember whether a key is currently depressed
	private boolean keyDown = false;
	
	public void begin() {
		
		/* This code will make it so the window cannot be resized */
		/*
		Container c = this;
		while(! (c instanceof Frame)) {
			c = c.getParent();
		}
		((Frame)c).setResizable(false);
		*/
		FilledRect background = new FilledRect(0,0,WIDTH, HEIGHT, canvas);
		background.setColor(Color.BLACK);
		
		
		Image shipImage = getImage("ship.png");
		ship = new Mothership(shipImage, WIDTH/2, 675, SHIP_WIDTH, SHIP_HEIGHT, canvas);
		
		Image inv1 = getImage("invader1.png");
		Image inv2 = getImage("invader2.png");
		Image inv3 = getImage("invader3.png");
		Image inv4 = getImage("invader5.png");
		Image inv5 = getImage("invader6.png");
		Image inv6 = getImage("invader7.png");
		//Invader i = new Invader(invader1Image, 20, 0, canvas);
		//Invader i2 = new Invader(invader1Image, 500, 0, canvas);
		Fleet f = new Fleet(inv1, inv2, inv3, inv4, inv5, inv6, canvas);
		
		for ( int row = 0 ; row < f.getFleet().length ; row++ ) {
			for ( int col = 0 ; col < f.getFleet()[row].length ; col++ ) {
				Invader invader = f.getFleet()[row][col];
				ship.addShipTarget(invader.getImage());
			}
		}
		
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
	}
	

	
	public void onMouseClick(Location l) {
		//f.toggleRunning();
	}
	
	
	// Handle the arrow keys by telling the ship to go in the direction of the arrow.
	public void keyTyped(KeyEvent e) {
		
		if ( e.getKeyCode() == KeyEvent.VK_SPACE || 
				 e.getKeyCode() == KeyEvent.VK_UP ) {
				
			    // insert code to handle press of up arrow or space bar
				ship.shoot();
				
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT ) {

		    // insert code to handle press of left arrow
			ship.left(true);

		} 
		else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			
			// insert code to handle press of right arrow
			ship.right(true);
			
        }
		
	}

	// Remember that the key is no longer down.
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT ) {

		    // insert code to handle press of left arrow
			ship.left(false);

		} 
		else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			
			// insert code to handle press of right arrow
			ship.right(false);
			
        }
		
		keyDown = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT || 
                    e.getKeyCode() == KeyEvent.VK_RIGHT ) {
		    // insert code to handle key release (optional stopping of base)
		}
	}

	// Handle the pressing of the key the same as typing.
	public void keyPressed(KeyEvent e)
	{
		
		if (!keyDown) {
			keyTyped(e);
		}
		
		keyDown = true;
		
	}
	
    public static void main(String[] args) { 
        new SpaceInvaders().startController(WIDTH, HEIGHT); 
	}
	
}
