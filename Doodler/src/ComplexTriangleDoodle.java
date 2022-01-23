import objectdraw.*;
import java.awt.Color;

public class ComplexTriangleDoodle implements TriangleDoodle {
	
	// lines of the triangle
	// line left, line right, and line bottom
	private Line ll;
	private Line lr;
	private Line lb;
	
	// inner triangles
	// triangle left, trinalge right, triangle top
	private TriangleDoodle tl;
	private TriangleDoodle tr;
	private TriangleDoodle tt;

	public ComplexTriangleDoodle(Location v1, Location v2, Location v3, DrawingCanvas canvas) {
		
		// create outer lines
		ll = new Line(v1, v3, canvas);
		lr = new Line(v2, v3, canvas);
		lb = new Line(v1,v2,canvas);
	}
	
	public void move(double dx, double dy) {
		
	}
}
