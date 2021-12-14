import java.util.ArrayList;
import objectdraw.*;

//shootable is an interface that is used when we need to pass in an object into bullet class, to let the bullet know what object 
//is shooting the bullet. Shootable can either be a mothership of an invader.

//Any Shootable needs to have methods below
public interface Shootable{
	
	public VisibleImage getImage();
	
	public ArrayList<VisibleImage> getTargets();
	
	public boolean isMothership();
	

}