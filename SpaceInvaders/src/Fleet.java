import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Fleet extends ActiveObject {
	
	private static final int INVADER_ROW = 6;
	private static final int INVADER_COL = 9;
	private Invader[][] fleet;
	private final int MOVEDISTANCE = 60;
	private SpaceInvaders si;
	private boolean running;
	private DrawingCanvas canvas;
	private static int speed = 700;
	
	public Fleet ( Image a, Image b, Image c, Image d, Image e, Image f, DrawingCanvas canvas, SpaceInvaders s) {
		
		//new 2d array of invaders
		fleet = new Invader [ INVADER_ROW ][ INVADER_COL ];
		Image i;
		si = s;
		running = true;
		this.canvas = canvas;
		
		//get images for the rows of invaders
		for ( int row = 0 ; row < fleet.length ; row++ ) {
			
			if (row == 0) i = a;
			else if(row == 1) i = b;
			else if(row == 2) i = c;
			else if(row == 3) i = d;
			else if(row == 4) i = e;
			else i = f;
			
			//fill 2d arrays with new invaders 
			for ( int col = 0 ; col < fleet[row].length ; col++ ) {
				
				fleet[row][col] = new Invader(i, 40*(col) + 20*(col+1), 60*row, canvas, si);
			}
		}
		
		start();
		
	}
	
	public Invader[][] getFleet() {
		return fleet;
	}
	
	//iterate through 2d array and return array of invaders in the frontRow of each column
	public ArrayList<Invader> frontRow () {
		
		ArrayList<Invader> frontRow = new ArrayList<Invader>();
		for (int col = 0; col < INVADER_COL; col++) {
			for (int row = INVADER_ROW - 1; row >= 0; row--) {
				if (! fleet[row][col].getImage().isHidden()) {
					frontRow.add(fleet[row][col]);
					break;
				}
			}
		}
		return frontRow;
	}
		
		
	//add targets the fleet should be shooting at
	public void addFleetTarget(Mothership m) {
		
		for ( int row = 0 ; row < fleet.length ; row++ ) {
			for ( int col = 0 ; col < fleet[row].length ; col++ ) {
				fleet[row][col].addinvaderTarget(m.getImage());
			}
		}
	}
	
	//iterate through the 2d array to find how many invaders are hidden to calculate the score
	public int calcScore () {
		
		int score = 0;
		
		for ( int row = 0 ; row < fleet.length ; row++ ) {
			
			for ( int col = 0 ; col < fleet[row].length ; col++ ) {
					
					if ( fleet[row][col].getImage().isHidden() ) {
						
						score += 10;
					}
					
				}
			}
		
		return score;
	}
	
	//end game method that hides all invaders in the fleet and erease all the bullets shot
	public void endGame() {
		
		running = false;
		
		for ( int row = 0 ; row < fleet.length ; row++ ) {
			
			for ( int col = 0 ; col < fleet[row].length ; col++ ) {
					
				if (!fleet[row][col].getImage().isHidden()) {
					fleet[row][col].getImage().hide();
				}
				fleet[row][col].eraseBullets();
			}
		}
	}
	
	public static void changeSpeed(int difficulty) {
		if (difficulty == 1) {
			speed = 1000;
		}
		else if (difficulty == 2) {
			speed = 800;
		}
		else if (difficulty == 3){
			speed = 600;
		}
	}
	
	public static int getSpeed() {
		if (speed == 1000) {
			return 1;
		}
		else if (speed == 800) {
			return 2;
		}
		else {
			return 3;
		}
	}
	
	public void run() {
		
		int direction = 1;
		int counter = 1;
		
		while (true) {
			
			while ( running ) {
				
				//if the invader has reached the bottom of the screen, end the game, invaders win
				// or if any of the front row invaders touch the ship
				for (Invader i : frontRow()) {
					for (VisibleImage vi : i.getTargets()) {
						if (i.getImage().getLocation().getY() + i.getImage().getHeight() >= canvas.getHeight() ||
								i.getImage().overlaps(vi)) {
							System.out.println("Score 1" + calcScore());
							si.endGame(false);
						}
					}
				}
					
				
				//if score is 540 end the game, mothership wins
				if (calcScore() >= 540) {
					si.endGame(true);
				}
				
				for ( int row = 0 ; row < fleet.length ; row++ ) {
					
					for ( int col = 0 ; col < fleet[row].length ; col++ ) {
						
						// total width of array --> 540
						// width of canvas --> 800
						// subtract margin of 20
						// 9 invaders across
						// moves a total of 4 times --> 60 each time
						
						if (counter%5 == 0 && counter != 0) {
							
							fleet[row][col].getImage().move(0, MOVEDISTANCE);
						}
						else {
							
							fleet[row][col].getImage().move(MOVEDISTANCE*direction, 0);
						}
					}
				}
				
				//increment speed
				if (counter%5 == 0 && counter != 0) {
					direction = direction * -1;
					speed -= 50;
				}
				
				counter ++;
				
				//pick one invader from the front row to shoot 
				if (frontRow().size() > 0) {
					Invader chosenOne = frontRow().get( (int) (Math.random() * frontRow().size()) );
				
				chosenOne.shoot();
				}
				
				if (speed <= 0) {
					speed = 700;
				}
				pause(speed);
				
			}
		
		}
	}
}