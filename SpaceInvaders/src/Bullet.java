import java.awt.Color;
import java.util.ArrayList;
import objectdraw.*;

public class Bullet extends ActiveObject {
    private FilledRect b;
    private DrawingCanvas c;
    
    private ArrayList<VisibleImage> targets;
    private Shootable ship;
    private SpaceInvaders si;
    
    private SoundPlayer p;
    private SoundPlayer l;
    
    public Bullet ( double x , double y , DrawingCanvas c, Shootable s, SpaceInvaders space) {
        
    	//get the target of the bullet
    	targets = s.getTargets();
    	ship = s;
    	si = space;
        b = new FilledRect ( x , y , 4 , 15 , c );
        //bullet color 
    	if (s.isMothership()) b.setColor(Color.white);
    	else b.setColor(Color.yellow);
        this.c = c;
        
		p = new SoundPlayer ("invaderdies.wav");
		l = new SoundPlayer ("mothershipdies.wav");
		
        start();
        
    }
    
    public FilledRect getImage() {
    	return b;
    }
    
    public void run () {
    	
    	//while the bullet is in the window
        while ( b.getY() < c.getHeight() && b.getY() > 0 ) {
        		
        		//iterate through targets 
        		for (VisibleImage i : targets) {
        			
        			//check if bullet is hitting the target
        			if (b.overlaps(i) && !i.isHidden() && !b.isHidden()) {
	
        				//if thing shooting object is a mothership
        				if (ship.isMothership()) {
        					
        					//hide the invader and hide the bullet
        					
        					p.play();
        					
        					i.hide();
        					b.hide();
        					break;
        					
        				}
        				
        				//if the thing shooting is an invader
        				else {
        					
        					//call endgame method because mothership has died
        					si.endGame(false);
        					l.play();
        					break;
        					
        				}
        			}
        			
    
        		}
        		
        		//move upwards if bullet is shot from mothership and move downwards if bullet is shot from mothership
        		if (ship.isMothership()) {
        			b.move(0,-3);
        		}
        		else {
        			b.move(0,3);
        		}
        		
                pause(10);
                
        	}
    	
        	//remove the bullet if it has gone off the screen
        	b.removeFromCanvas();
        	
        }
     
}