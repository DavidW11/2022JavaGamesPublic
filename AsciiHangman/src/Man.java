import java.util.Arrays;

public class Man {
	
	/*  An ASCII Hangman looks like this:
	 *   O 
	 *  \|/
	 *  / \
	 */

	protected static final int MAX_INCORRECT = 6;
	// create instance variable to track the number of incorrect guesses
	protected int numIncorrect;
	protected char[] body;
	
	public Man() {
		/* TODO: Intiaialize the Man object */
		// initialize number of incorrect guesses to 0
		numIncorrect = 0;
		// the 2-D representation of the body is a
		// 4 column (including new lines) by 3 row grid,
		// making the 1-D array of length 12
		body = new char[12];
		// set default values of array
		for (int i = 0; i < body.length; i++) {
			// set right hand "column" to new lines
			if ((i-3)%4 == 0) {
				body[i] = '\n';
			}
			// set everything else to blank spaces
			else {
				body[i] = ' ';
			}
		}
		
	}
	
	public boolean isAlive() {
		/* TODO: Check if the man is alive */
		if (numIncorrect < MAX_INCORRECT) {
			return true;
		}
		// if the number of incorrect guesses has reached the maximum number, 
		// then return false (AKA the man is dead)
		else {
			return false;
		}
	}
	
	public void hang() {
		/* TODO: modify the man data to reflect
		 * a new incorrect guess. 
		 */
		// increment numIncorrect by 1
		numIncorrect++;
		// add certain body parts to the man 
		// depending on how many incorrect guesses the player has made
		switch (numIncorrect) {
		case 0: break;
		case 1: body[1] = 'O'; break;
		case 2: body[5] = '|'; break;
		case 3: body[4] = '\\'; break;
		case 4: body[6] = '/'; break;
		case 5: body[8] = '/'; break;
		case 6: body[10] = '\\'; break;
		}
	}
	
	public String toString() {
		/* TODO: Render the man as a string. */
		String s = "";
		// concatenate all values of the body array into a printable string
		for (char i:body) {
			s = s + String.valueOf(i);
		}
		// return the string
		return s;
	}
	
	protected char[] toCharArray() {
		/* TODO: Return the relevant data */
		// return the body array (used in the gallows class)
		return body;
	}
	
	/* Again, this main method is provided to assist
	 * with testing. 
	 */
	public static void main(String[] args) {
		Man m = new Man();
		for(int i=0; i<Man.MAX_INCORRECT; i++) {
			m.hang();
			System.out.println(m);
		}
	}
	
}
