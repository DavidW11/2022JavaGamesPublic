import objectdraw.*;


public class Man {
	protected FramedOval head;
	protected DrawableInterface[] bodyParts;
	
	protected static final int MAX_INCORRECT = 6;
	protected static final int HEAD_SIZE = 80;
	protected static final int BODY_SIZE = 80;
	protected static final int ARM_LENGTH = 50;
	protected int numIncorrect;
	
	public Man(double xPos, double yPos, DrawingCanvas canvas) {
		/* TODO: Initialize all of the man's body parts.
		 * Then, use the clear method to hide them all. 
		 * The given (xPos, yPos) specifies the coordinates
		 * for the top of the man's head.
		 */
		
		// y coordinate for neck of man
		double neckY = yPos + HEAD_SIZE;
		// y coordinate for crotch of man
		double bottomY = yPos + HEAD_SIZE + BODY_SIZE;
		// create man
		Line body = new Line(xPos, neckY, xPos, bottomY, canvas);
		Line armRight = new AngLine(xPos, neckY + 30, ARM_LENGTH, 3.14/6, canvas);
		Line armLeft = new AngLine(xPos, neckY + 30, ARM_LENGTH, 5*3.14/6, canvas);
		Line legRight = new Line(xPos, bottomY, xPos + 40, bottomY + 50, canvas);
		Line legLeft = new Line(xPos, bottomY, xPos - 40, bottomY + 50, canvas);
		head = new FramedOval(xPos - (HEAD_SIZE/2), yPos, HEAD_SIZE, HEAD_SIZE, canvas);
		
		bodyParts = new DrawableInterface[6];
		bodyParts[0] = head;
		bodyParts[1] = body;
	    bodyParts[2] = armLeft;
	    bodyParts[3] = armRight;
		bodyParts[4] = legLeft;
		bodyParts[5] = legRight;
		
		this.clear();
	}
	
	public void clear() {
		/* TODO: Hide all of the man's body parts */
		for (int i = 0; i < bodyParts.length; i++) {
			bodyParts[i].hide();
		}
	}
	
	public void hang() {
		/* TODO: Hang the man */
		numIncorrect++;
		
		switch (numIncorrect) {
		case 0: break;
		case 1: bodyParts[0].show(); break;
		case 2: bodyParts[1].show(); break;
		case 3: bodyParts[2].show(); break;
		case 4: bodyParts[3].show(); break;
		case 5: bodyParts[4].show(); break;
		case 6: bodyParts[5].show(); break;
		}
	}
	
	public boolean isAlive() {
		/* TODO: Return true if the man is not fully
		 * hanged.  Otherwise, return false. 
		 */
		if (bodyParts[5].isHidden() == true) {
			return true;
		}
		return false;
	}

	public void erase(){
		// to reset the man and start a new game
		numIncorrect = 0;
		clear();
	}
}
