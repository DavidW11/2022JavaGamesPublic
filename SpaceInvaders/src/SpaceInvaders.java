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
	protected FilledRect background;
	protected Mothership ship;
	protected Fleet f;
	protected Text t;
	protected Text b;
	protected Text diff1;
	protected Text diff2;
	protected Text diff3;
	protected FramedRect but1;
	protected FramedRect but2;
	protected FramedRect but3;
	protected Text speedText;
	
	private SoundPlayer p;
	
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
		
		p = new SoundPlayer ("music.wav");
		p.play();
		
		background = new FilledRect(0,0,WIDTH, HEIGHT, canvas);
		background.setColor(Color.WHITE);
		
		t = new Text ( "Click to Start the Game." , 170 , 370 , canvas );
		t.setFontSize(40);
		
		b = new Text ( "Click to play again." , 210 , 430 , canvas );
		b.hide();
		
		speedText = new Text("Speed:" + Fleet.getSpeed(), 10, 10, canvas);
		speedText.setFontSize(15);
		
		diff1 = new Text ( " Speed 1", 250, 250, canvas );
		diff1.setFontSize(20);
		diff2 = new Text ( " Speed 2", 350, 250, canvas );
		diff2.setFontSize(20);
		diff3 = new Text ( " Speed 3", 450, 250, canvas );
		diff3.setFontSize(20);
		
		but1 = new FramedRect(diff1.getX(), diff1.getY(), diff1.getWidth(), diff1.getHeight(), canvas);
		but2 = new FramedRect(diff2.getX(), diff2.getY(), diff2.getWidth(), diff2.getHeight(), canvas);
		but3 = new FramedRect(diff3.getX(), diff3.getY(), diff3.getWidth(), diff3.getHeight(), canvas);
				
		//f = new Fleet(inv1, inv2, inv3, inv4, inv5, inv6, canvas, this);
		
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
	}

	
	public void onMouseClick(Location l) {
		
		if ( (!t.isHidden()  || !b.isHidden() ) && 
				!diff1.contains(l) && !diff2.contains(l) && !diff3.contains(l)) {
			
			Fleet.changeSpeed(Fleet.getSpeed());
			background.setColor(Color.BLACK);
			t.hide();
			b.hide();
			diff1.hide();
			diff2.hide();
			diff3.hide();
			but1.hide();
			but2.hide();
			but3.hide();
			speedText.hide();
			
			Image shipImage = getImage("ship.png");
			ship = new Mothership(shipImage, WIDTH/2, 675, SHIP_WIDTH, SHIP_HEIGHT, canvas, this);
			
			Image inv1 = getImage("invader1.png");
			Image inv2 = getImage("invader2.png");
			Image inv3 = getImage("invader3.png");
			Image inv4 = getImage("invader5.png");
			Image inv5 = getImage("invader6.png");
			Image inv6 = getImage("invader7.png");

			f = new Fleet(inv1, inv2, inv3, inv4, inv5, inv6, canvas, this);
			
			for ( int row = 0 ; row < f.getFleet().length ; row++ ) {
				for ( int col = 0 ; col < f.getFleet()[row].length ; col++ ) {
					Invader invader = f.getFleet()[row][col];
					ship.addShipTarget(invader.getImage());
				}
			}
			
			f.addFleetTarget(ship);
		}
		
		if (but1.contains(l) && !diff1.isHidden()) {
			Fleet.changeSpeed(1);
		}
		else if (but2.contains(l) && !diff2.isHidden()) {
			Fleet.changeSpeed(2);
		}
		else if (but3.contains(l) && !diff3.isHidden()) {
			Fleet.changeSpeed(3);
		}	
		speedText.setText("Speed: " + Fleet.getSpeed());
		
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
	public void keyPressed(KeyEvent e) {
		
		if (!keyDown) {
			keyTyped(e);
		}
		
		keyDown = true;
		
	}
	
	public void endGame(boolean win) {
		
		int score = f.calcScore();
		System.out.println("Score 2" + score);
		f.endGame();
		ship.eraseBullets();
		ship.getImage().hide();
		
		
		if (win) {
			t.setText("You Won! Your score: " + score + "/540 points.");
			t.setColor(Color.green);
			b.setColor(Color.green);
			diff1.setColor(Color.green);
			diff2.setColor(Color.green);
			diff3.setColor(Color.green);
			but1.setColor(Color.green);
			but2.setColor(Color.green);
			but3.setColor(Color.green);
			speedText.setColor(Color.green);
		}
		else {
			t.setText("Game over! Your score: " + score + "/540 points.");
			t.setColor(Color.red);
			b.setColor(Color.red);
			diff1.setColor(Color.red);
			diff2.setColor(Color.red);
			diff3.setColor(Color.red);
			but1.setColor(Color.red);
			but2.setColor(Color.red);
			but3.setColor(Color.red);
			speedText.setColor(Color.red);
		}
		
		t.moveTo(80, 340);
		t.setFontSize(35);
		t.show();
		b.setFontSize(35);
		b.show();
		diff1.show();
		diff2.show();
		diff3.show();
		but1.show();
		but2.show();
		but3.show();
		speedText.show();
	}
	
    public static void main(String[] args) { 
        new SpaceInvaders().startController(WIDTH, HEIGHT); 
	}
	
}