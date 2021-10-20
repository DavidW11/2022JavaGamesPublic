import objectdraw.*;

public class Gallows {

	protected FilledRect base;
	protected FilledRect beam;
	protected FilledRect crossbeam;
	protected FilledRect rope;
	protected Man man;
	
	protected static final int BEAM_WIDTH = 10;

	
	public Gallows(double xPos, double yPos, DrawingCanvas canvas) {
		/* TODO: Initialize the instance variables that constitute the 
		 * frame of the gallows. The given (xPos, yPos) specifies the
		 * coordinates of the bottom left corner of the gallows.  
		 */
		double baseLength = 100;
		double beamLength = 300;
		double crossbeamLength = 90;
		double ropeLength = 50;
		
		base = new FilledRect(xPos, yPos, baseLength, BEAM_WIDTH, canvas);
		beam = new FilledRect(xPos + baseLength/2, yPos-beamLength, BEAM_WIDTH, beamLength, canvas);
		crossbeam = new FilledRect(xPos + baseLength/2, yPos-beamLength, crossbeamLength, BEAM_WIDTH, canvas);
		rope = new FilledRect(xPos + baseLength/2 + crossbeamLength, yPos - beamLength, BEAM_WIDTH, ropeLength, canvas);
		
		man = new Man(xPos + baseLength/2 + crossbeamLength + BEAM_WIDTH/2, yPos - beamLength + ropeLength, canvas);
		//man = new Man(xPos + BEAM_WIDTH * 16, yPos - BEAM_WIDTH * 25, canvas);
		
		
	}
	
	public void hang() {
		man.hang();
	}
	
	public boolean isAlive() {
		return man.isAlive();
	}
	
	public void clear() {
		/* TODO: Hide all of the elements of the 
		 * gallows, and clear the man.
		 */
		base.hide();
		beam.hide();
		crossbeam.hide();
		rope.hide();
		man.clear();
	}

	public void erase() {
		man.erase();
	}
}
