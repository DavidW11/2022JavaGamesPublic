import java.awt.Image;
import java.util.ArrayList;
import objectdraw.*;
// width 40
// gap of 20 in between each

public class Invader extends ActiveObject implements Shootable {

	private VisibleImage inv;
	private DrawingCanvas c;
	private boolean running;
	private final int WIDTH = 40;
	private final int MOVEDISTANCE = 60;
	
	private ArrayList<VisibleImage> invaderTargets;
	
	
	
	public Invader ( Image i , double x , double y , DrawingCanvas c ) {
		
		
		inv = new VisibleImage(i, x, y, WIDTH, WIDTH, c);
		invaderTargets = new ArrayList<VisibleImage>();
		running = false;
		this.c = c;
		
		start();
		
	}
	
	public void addinvaderTarget(VisibleImage m) {
    	invaderTargets.add(m);
    }
	
	public void toggleRunning () {
		running = !running;
	}
	
	public VisibleImage getImage() {
		return inv;
	}
	
	public ArrayList<VisibleImage> getTargets() {
		return invaderTargets;
	}
	
	public boolean isMothership() {
		return false;
	}
	
	
	public void die() {
    	inv.removeFromCanvas();
    }
	
	public void run() {
		
		while ( true ) {
			
			int direction = 1;
			int speed = 700;
					
			while ( running ) {
				speed += 100;
				
				for(int i = 0; i < 4; i++) {
					// total width of array --> 540
					// width of canvas --> 800
					// subtract margin of 20
					// 9 invaders across
					// moves a total of 4 times --> 60 each time
					inv.move(MOVEDISTANCE*direction, 0);
					pause(speed);
				}
				
				inv.move(0, MOVEDISTANCE);
				direction *= -1;	
				pause(speed);
			}
		}
	}
}
