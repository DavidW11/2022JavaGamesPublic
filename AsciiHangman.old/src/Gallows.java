import java.util.Arrays;

public class Gallows {
	
	/* Gallows look like this:
	 *    ____
	 *   |    |
	 *   |    O
	 *   |   \|/
	 *   |   / \
	 * __|__
	 */
	
	protected Man man;
	protected char[] frame;

	public Gallows() {
		/* TODO: Initialize instance variables and 
		 * otherwise construct the Gallows object.
		 */
		// initialize man and frame objects
		man = new Man();
		// the 2-D visualiztion of the frame is 10 columsn wide (including new spaces) and 6 rows tall,
		// making the 1-D array of length 60
		frame = new char[60];
		// set the default value of all items in the frame array to blank spaces
		for (int i = 0; i < 59; i++) {
			frame[i] = ' ';
		}
	}
	
	public void makeNewLines() {
		// add new lines down the last "column" of the array
		// AKA all indexes of value 9 plus some multiple of 10
		// for future reference: I will put quotations around "column" and "row" to
		// show that I am referring to the 2-D representation and not the 1-D array itself
		for (int i = 9; i <= (frame.length); i += 10) {
			frame[i] = '\n';
		}
	}	
	
	public void makeCenterPost() {
		/* TODO: Modify the frame to include
		 * the central post.
		 */
		// add '|' down the 3rd "column" (excluding the top "row")
		// to make the central post
		for(int i = 12; i <= frame.length; i += 10) {
			frame[i] = '|';
		}
	}
	
	public void makeBeam() {
		/* TODO: Modify the frame to include
		 * the top beam. 
		 */
		// add underscores along a section of the top "row"
		// to make the beam
		for (int i = 3; i < 7; i++) {
			frame[i] = '_';
		}
		
	}
	
	public void makeBase() {
		/* TODO: Modify the frame to include the
		 * base of the gallows. 
		 */
		// add underscores in the appropriate places to create the base
		frame[50] = '_';
		frame[51] = '_';
		frame[53] = '_';
		frame[54] = '_';
	}
	
	public void makeRope() {
		/* TODO: Modify the frame to include the rope. */
		frame[17] = '|';
	}
	
	public void addMan() {
		// add references to the man array
		// to corresponding places in the frame array
		// thus, changing the man array will also modify the gallows as needed
		frame[27] = man.toCharArray()[1];
		frame[36] = man.toCharArray()[4];
		frame[37] = man.toCharArray()[5];
		frame[38] = man.toCharArray()[6];
		frame[46] = man.toCharArray()[8];
		frame[48] = man.toCharArray()[10];
	}
	
	public void hang() {
		/* TODO: hang the hangman */
		// use man object to call hang() method
		man.hang();
		this.addMan();
	}
	
	public boolean isAlive() {
		/* TODO: Check if the hangman is alive */
		// use man object to call isAlive() method
		return man.isAlive();
	}
	
	public String toString() {
		/* TODO: Render the hangman as a string
		 */
		// call all methods to build the gallows
		String s = "";
		this.makeNewLines();
		this.makeCenterPost();
		this.makeBeam();
		this.makeBase();
		this.makeRope();
		
		// concatenate all items in the array to create a printable string
		for (char i:frame) {
			s = s + String.valueOf(i);
		}
		// return the string
		return s;
	}
	
	/* This code is included to allow you to test the
	 * Gallows independently from the Hangman code. 
	 */
	public static void main(String[] args) {
		Gallows g = new Gallows();
		System.out.println(g);
		
		for(int i=0; i< Man.MAX_INCORRECT; i++) {
			g.hang();
			System.out.println(g);
		
		}
	}
}

