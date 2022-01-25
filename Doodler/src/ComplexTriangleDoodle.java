import objectdraw.*;
import java.awt.Color;
// TODO ADD COLOR
public class ComplexTriangleDoodle implements TriangleDoodle {
	
	// lines of the triangle
	// line left, line right, and line bottom
	private Line ll;
	private Line lr;
	private Line lb;
	
	// inner triangles
	// triangle left, triangle right, triangle top
	private TriangleDoodle tl;
	private TriangleDoodle tr;
	private TriangleDoodle tt;

	public ComplexTriangleDoodle(Location v1, Location v2, Location v3, Color color, DrawingCanvas canvas) {
		
		// create outer lines
		ll = new Line(v1, v3, canvas);
		lr = new Line(v2, v3, canvas);
		lb = new Line(v1,v2,canvas);
		
		// set colors
		ll.setColor(color);
		lr.setColor(color);
		lb.setColor(color);
		
		// find midpoints
		Location ml = getMidpoint(v1,v3);
		Location mr = getMidpoint(v2,v3);
		Location mb = getMidpoint(v1,v2);
		
		// find edge lengths
		double el = v1.distanceTo(v3);
		double er = v2.distanceTo(v3);
		double eb = v1.distanceTo(v2);
		
		// if any edges are longer than x pixels, create new complex triangles
		if (el > 30 || er > 30 || eb > 30) {
			tl = new ComplexTriangleDoodle(v1, mb, ml, Color.RED, canvas);
			tr = new ComplexTriangleDoodle(mb, v2, mr, Color.BLUE, canvas);
			tt = new ComplexTriangleDoodle(ml, mr, v3, Color.GREEN, canvas);
		}
		// if no edges > 6 pixels, create empty triangles (base case)
		else {
			tl = new EmptyTriangleDoodle();
			tr = new EmptyTriangleDoodle();
			tt = new EmptyTriangleDoodle();
		}
	}
	
	private Location getMidpoint(Location l1, Location l2) {
		return new Location((l1.getX() + l2.getX())/2, (l1.getY() + l2.getY())/2); 
	}
	
	public void move(double dx, double dy) {
		// move outer lines
		ll.move(dx, dy);
		lr.move(dx, dy);
		lb.move(dx, dy);
		
		// move inner triangles
		tl.move(dx,dy);
		tr.move(dx,dy);
		tt.move(dx,dy);
	}
}
