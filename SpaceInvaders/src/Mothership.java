import java.awt.Image;
import java.util.ArrayList;
import objectdraw.*;


public class Mothership extends ActiveObject implements Shootable {
	
	private VisibleImage vi;
	private DrawingCanvas c;
	boolean right = false;
	boolean left = false;
	boolean shooting = false;
	
	private ArrayList<Bullet> bullets;
	private ArrayList<VisibleImage> shipTargets;
	
	public Mothership(Image i, double x, double y, double width, double height, DrawingCanvas c ) {
		vi = new VisibleImage(i, x, y, width, height, c);
		
		bullets = new ArrayList<Bullet>();
		
		shipTargets = new ArrayList<VisibleImage>();
		
		this.c = c;
		
		start();
	}
	
	public void addShipTarget(VisibleImage i) {
    	shipTargets.add(i);
    }
	
	public boolean isMothership() {
		return true;
	}
	
	public void run() {
		while (true) {
			if (right) {
				vi.move(3, 0);
			}
			if (left) {
				vi.move(-3, 0);
			}
			
			pause(10);
		}
	}
	
	public void right(boolean b) {
		right = b;
	}
	
	public void left(boolean b) {
		left = b;
	}
	
	public void shoot() {
		bullets.add (new Bullet ( vi.getX() + vi.getWidth()/2 - 2 , vi.getY() - 15 , c, this));
	}
	
	public VisibleImage getImage() {
		return vi;
	}
	
	public ArrayList<VisibleImage> getTargets() {
		return shipTargets;
	}
	
	
	
	
}
