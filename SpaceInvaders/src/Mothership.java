import java.awt.Image;
import java.util.ArrayList;
import objectdraw.*;


public class Mothership extends ActiveObject implements Shootable {
	
	private VisibleImage vi;
	private DrawingCanvas c;
	
	boolean right = false;
	boolean left = false;
	
	private ArrayList<VisibleImage> shipTargets;
	private SpaceInvaders si;
	
	private ArrayList<Bullet> bullets;
	
	private SoundPlayer p;
	
	public Mothership(Image i, double x, double y, double width, double height, DrawingCanvas c, SpaceInvaders s) {
		
		//creates visible image of mothership
		vi = new VisibleImage(i, x, y, width, height, c);
		si = s;
		//array of ship targets
		shipTargets = new ArrayList<VisibleImage>();
		//array of bullets shot
		bullets = new ArrayList<Bullet>();
		
		p = new SoundPlayer ("mshoot.wav");
		
		this.c = c;
		
		start();
	}
	
	public void right(boolean b) {
		right = b;
	}
	
	public void left(boolean b) {
		left = b;
	}
	
	//have the mothership shoot if the mothership is not hidden, add bullet to bullets array
	public void shoot() {
		
		if (!vi.isHidden()) {
			
			bullets.add(new Bullet ( vi.getX() + vi.getWidth()/2 - 2 , vi.getY() - 15 , c, this, si));
			p.play();
		}
	
	}
	
	public VisibleImage getImage() {
		return vi;
	}
	
	public ArrayList<VisibleImage> getTargets() {
		return shipTargets;
	}
	
	public void addShipTarget(VisibleImage i) {
    	shipTargets.add(i);
    }
	
	public boolean isMothership() {
		return true;
	}
	
	//erase all bullets shot by mothership
	public void eraseBullets() {
		
		for (Bullet b : bullets) {
			b.getImage().hide();
		}
		bullets = new ArrayList<Bullet>();
	}
	
	
	public void run() {
		
		//movement of the mothership
		while (!vi.isHidden()) {
			if (right && vi.getX() < c.getWidth() - vi.getWidth()) {
				vi.move(3, 0);
			}
			if (left && vi.getX() > 0) {
				vi.move(-3, 0);
			}
			
			pause(10);
		}
	}
}