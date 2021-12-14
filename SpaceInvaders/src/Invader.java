import java.awt.Image;
import java.util.ArrayList;
import objectdraw.*;
// width 40
// gap of 20 in between each

public class Invader implements Shootable {

	private VisibleImage inv;
	private DrawingCanvas c;
	private final int WIDTH = 40;
	private ArrayList<VisibleImage> invaderTargets;
	private SpaceInvaders si;
	private ArrayList<Bullet> bullets;
	private SoundPlayer p;
	
	public Invader ( Image i , double x , double y , DrawingCanvas c, SpaceInvaders s) {
		
		//create visible image of invader
		inv = new VisibleImage(i, x, y, WIDTH, WIDTH, c);
		//array of invader targets
		invaderTargets = new ArrayList<VisibleImage>();
		//array of bullets shot by invader
		bullets = new ArrayList<Bullet>();
		si = s;
		this.c = c;
		
		p = new SoundPlayer ("invshoot.wav");
	}
	
	//add target of the invader 
	public void addinvaderTarget(VisibleImage m) {
    	invaderTargets.add(m);
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
	
	//erase all the bullets shot from invader
	public void eraseBullets() {
		for (Bullet b : bullets) {
			b.getImage().hide();
		}
		bullets = new ArrayList<Bullet>();
	}
	
	//the invader shoots, add bullet to bullets array
	public void shoot() {
		
		bullets.add(new Bullet ( inv.getX() + inv.getWidth()/2 - 2 , inv.getY() + 40 , c, this, si));
		p.play();
	}
}