import objectdraw.Location;
import objectdraw.WindowController;
import java.awt.Color;

public class StairController extends WindowController {

	private StairInterface doodle;
	private Location current;
	
	public void begin() {
		Location v1 = new Location(100, 400);
		double sideLength = 128;
		doodle = new VariegatedStairs(v1, sideLength, 225, canvas);
		resize(800,800);
	}
	
	public void onMousePress(Location l) {
		current = l;
	}
	
	public void onMouseDrag(Location l) {
		double dx = l.getX() - current.getX();
		double dy = l.getY() - current.getY();
		doodle.move(dx, dy);
		current = l;
	}
	
	public static void main(String[] args) {
		StairController s = new StairController();
		s.startController();
	}
}
