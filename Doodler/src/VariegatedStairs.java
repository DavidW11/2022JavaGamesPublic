import java.awt.Color;
import objectdraw.*;

public class VariegatedStairs implements StairInterface {
	
	// center square
	private FilledRect centerSquare;
	
	// outer squares
	private StairInterface topSquare;
	private StairInterface rightSquare;

	public VariegatedStairs(Location v1, double sideLength, int colorNum, DrawingCanvas canvas) {
		
		// create center square
		centerSquare = new FilledRect(v1, sideLength, sideLength, canvas);
		Color c = new Color(colorNum, colorNum, 255);
		centerSquare.setColor(c);
		
		// top left corner of the top square
		Location topOrigin = new Location(v1.getX(), v1.getY() - sideLength/2);
		// top left corner of the right square
		Location rightOrigin = new Location(v1.getX() + sideLength, v1.getY() + sideLength/2);
		
		// if the side length of the center square is greater than or equal to 3 - 
		// create more variegated stairs
		if (centerSquare.getWidth() >= 3) {
			topSquare = new VariegatedStairs(topOrigin, sideLength/2, colorNum - 30, canvas);
			rightSquare = new VariegatedStairs(rightOrigin, sideLength/2, colorNum - 30, canvas);
		}
		// if less than 3, create empty stairs
		else {
			topSquare = new EmptyStairs();
			rightSquare = new EmptyStairs();
		}
	}
	
	public void move(double dx, double dy) {
		// move center square
		centerSquare.move(dx, dy);
		
		// move outer squares
		topSquare.move(dx, dy);
		rightSquare.move(dx, dy);
	}
}





