/* Thought Questions:
 * 
 * 1)
 * Program A takes less time than B for all values of 0.001 < n < 29.718.
 * When graphing the functions A = 2^n/1000 and B = 1000*n^2, 
 * A is less than B for all values of n greater than 0.001 and less than 29.718
 * 
 * 2)
 * The complexity is O(n)
 * Because the program iterates through each character of the 2 strings in order to create
 * a new string of length n + n. In other words, the program iterates through 2n characters.
 * Thus, the complexity can be written as O(2n), or O(n).
 * 
 * 3)
 	public static int Binary (int num){
        if(num == 0){
            return 0;
        }
        else{
            return num % 2 + 10 * (Binary(num/2));
        }
    }
 */


import objectdraw.*;
import java.awt.Color;

public class GasketController extends WindowController {

	private TriangleDoodle doodle;
	private Location current;
	
	public void begin() {
		Location v1 = new Location(100, 600);
		Location v2 = new Location(500, 600);
		Location v3 = new Location(300, 264);
		doodle = new ComplexTriangleDoodle(v1, v2, v3, Color.WHITE, canvas);
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
		GasketController g = new GasketController();
		g.startController();
	}
}
