import java.awt.Color;
import java.util.ArrayList;
import objectdraw.*;

public class Bullet extends ActiveObject {
    private FilledRect b;
    private DrawingCanvas c;
    
    private ArrayList<VisibleImage> targets;
    private Shootable ship;
    
    
    public Bullet ( double x , double y , DrawingCanvas c, Shootable s) {
        
    	targets = s.getTargets();
    	ship = s;
        b = new FilledRect ( x , y , 4 , 15 , c );
    	if (s.isMothership()) b.setColor(Color.white);
    	else b.setColor(Color.yellow);
        this.c = c;
        start();
        
    }
    
    
    public void die() {
    	b.removeFromCanvas();
    }
    
    public void run () {
    	
        
        while (b.getX() < c.getWidth() && b.getY() < c.getHeight()) {
        	
        		for (VisibleImage i : targets) {
        			if (b.overlaps(i)) {
        				if (ship.isMothership()) {
        					
        					b.removeFromCanvas();
        					if (!i.isHidden()) {
        						i.removeFromCanvas();
        					}
        					targets.remove(i);
        					// add points
        				}
        			}
        		}
        		
        		if (ship.isMothership()) {
        			b.move(0,-3);
        		}
        		
                pause(10);
        	}
    	
        	
        	/*
        	else if (shipType.equals("invader")) {
        		for (Mothership m : invaderTargets) {
        			if (b.overlaps(m.getImage())) {
        				die();
        				m.die();
        			}
        		}
        		
        	}
        	*/

        
        b.removeFromCanvas();

    }
}