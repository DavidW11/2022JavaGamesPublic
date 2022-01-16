import structure5.Matrix;

/**
 * This module includes the functions necessary to keep track of the creatures
 * in a two-dimensional world. In order for the design to be general, the
 * interface adopts the following design:
 * <p>1. The contents are unspecified objects.
 * <p>2. The dimensions of the world array are specified by the client. <p>
 * There are many ways to implement this structure. HINT:
 * look at the structure.Matrix class. You should need to add no more than 
 * about ten lines of code to this file.
 */

public class World<E> {
	
	Matrix<E> m;
	
	/**
	 * This function creates a new world consisting of width 
	 * columns and height rows, each of which is numbered beginning at 0. 
	 * A newly created world contains no objects.
	 * @param w The width of the world that is to be created
	 * @param h The height of the world that is to be created
	 * @pre w > 0
	 * @pre h > 0
	 */
	
	public World(int w, int h)  {
		
		m = new Matrix<E>(h,w);
	}

	/**
	 * Returns the height of the world.
	 */
	public int height() {
		return (m.height());
	}

	/**
	 * Returns the width of the world.
	 */
	public int width() {
		return (m.width());
	}

	/**
	 * Returns whether pos is in the world or not. 
	 * @pre pos is a non-null position. 
	 * @post returns true if pos is an (x,y) location in the bounds of
	 *       the board.
	 */
	boolean inRange(Position pos) {
		return (pos.getX() <= (width()-1) &&
				pos.getX() >= 0 &&
				pos.getY() <= (height()-1) &&
				pos.getY()>= 0);
	}

	/**
	 * Sets a position on the board to contain c.
	 * @param c The object that is to be added
	 * @param pos Where c is to be added
	 * @pre pos is a non-null position on the board.
	 */
	public void set(Position pos, E c) {
		if (inRange(pos)) {
			m.set(pos.getY(), pos.getX(), c);
		}
	}

	/**
	 * Return the contents of a position on the board. 
	 * @pre pos is a non-null position on the board.
	 */
	public E get(Position pos) {
		if (inRange(pos)) {
			return m.get(pos.getY(), pos.getX());
		}
		else {
			return null;
		}
	}

	
	public static void main(String[] args) {
		// tests
		
		World<Integer> test = new World<Integer>(4,5);
		if (test.height() == 5) {
			System.out.println("1:Pass");
		}
		if (test.width() == 4) {
			System.out.println("2:Pass");
		}
		Position p1 = new Position(0,4);
		Position p2 = new Position(4,3);
		if (test.inRange(p1)) {
			System.out.println("3:Pass");
		}
		if (!test.inRange(p2)) {
			System.out.println("4:Pass");
		}
		test.set(p1, Integer.valueOf(10));
		if (test.get(p1).equals( Integer.valueOf(10) )) {
			System.out.println("5:Pass");
		}
		
	}
}
