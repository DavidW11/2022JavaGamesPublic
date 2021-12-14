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
 * Shootable Interface (used by both invaders and spaceship)
 * We used this interface to pass in which type of ship, invader or mothership, is the one shooting
 * for the Bullet class
 * We passed in a Shootable object and used methods outlined by the interface to access its targets
 * and determine if it is a mothership or invader
 * Another way to use this class would be to make the targets an array of Shootable objects, and utilize
 * its functionality accordingly, but we went implemented the first option.
 */

/* Thought Questions:
 * 
 * 1) 
 * For its release in 1978, Space Invaders introduced unique game features that undoubtedly
 * contributed to its success. Space Invaders popularized the shooting game concept, with
 * opponents that move and fight back, introducing a level of skill and strategy. The game also
 * brought a competitive atmosphere, by recording global/arcade achievements with a leader board.
 * The time limited aspect of the game, as Invaders slowly speed up, was also a novel and captivating
 * idea. This concept puts the user under pressure, increasing both frustration from losing and motivation
 * to win.I also think that the recognizable music and sounds of the game added to the its
 * success.
 * 
 * 
 * 2)
 * I would make a new class called Base, that displays a visible image on a certain location on the screen.
 * The class would include an integer instance variable that tracks its health. After 3 bases are initialized
 * in SpaceInvaders at the start of the game, they are all added to the target array of both the ship and the 
 * invaders. As the Bullet class checks if it overlaps with one of the targets, if the target is a Base object,
 * the bullet will hide itself and the class will call a mutator method in the Base class 
 * that decreases its health. Using an accessor method, if the health is 0, hide the Base image from the screen.
 *  
 * If I wanted to also have parts of the Base be removed by the Bullet, I would make the Base a collection of small 
 * Filled Rectangles, and remove the rectangle from the position that the bullet collides with it.
 * 
 * 
 * 
 * 3)
 * I would create new class called ufo that when constructed, would created a visible image that shows a ufo. 
 * It would extend Active Object and the run method would move it from one side of the screen to any other. 
 * ufo class will be Shootable and everytime the bullet moves it will check if it has hit ufo, the ufo will be 
 * added to the mothership’s targets. If the ufo makes it across the screen, 
 * call a function in the base class that destroys the base. If the bullet does overlap with the ufo,
 * hide the ufo and the bullet.
 */

/* Extensions:
 * 
 * User can select between 3 different speeds at the beginning of each game
 * If the user does not select anything, the same speed as the last game is kept,
 * and if it is the first game, the default speed is 2 (medium difficulty)
 * 
 * 
 * Music and Sounds:
 * Invader shooting
 * Mothership shooting
 * Background music
 * Invader hit
 * Mothership hit
 * 
 */


import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
	private boolean setup;
	
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
		
		setup = true;
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
			
			setup = false;
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
		
		if (!setup) {
			
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
		
	}

	// Remember that the key is no longer down.
	public void keyReleased(KeyEvent e) {
		
		if (!setup) {
		
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
		
		setup = true;
	}
	
	public ArrayList<Bullet> getMotherBullets() {
		return ship.getBullets();
	}
	
    public static void main(String[] args) { 
        new SpaceInvaders().startController(WIDTH, HEIGHT); 
	}
	
}