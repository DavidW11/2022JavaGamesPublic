/*
Thought Questions:

1. The expressions are only equal when a is an integer.
If a is an integer, (a/7*7) will give the highest number divisible by 7 less than a, 
so subtracting that from a is equivalent to a mod 7.
However, if a is a double, the right hand side of the equation will always return 0.0.

2. Subtract 1 from the sum before taking the sum mod 7. This will also decrease all remainders by one,
making Sunday correspond to 0.

3. We subtract 1 from months before March on leap years because leap years do not affect the days on Febuary 29 and
earlier. The adjusted algorithm already compensates for leap years by dividing the year by 4 and dropping the decimal
(leap years are divisible by 4, so no non-0 decimal is dropped and the sums increment by 1. This also increments
the remainders by one and compensates for the extra day).
Since the days before March are not affected, we have to manually subtract 1 in order to readjust the adjustement.
*/

import java.io.Console;
import java.util.Scanner;

public class Hangman {
	
	public static void main(String[] args) {
		// ask user to input secret word and create array for secret word
		System.out.println("Welcome to the ASCII Version of Hangman!");
		
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		char[] letters;
		Gallows gallow = new Gallows();
		
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			letters = c.readPassword(prompt);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]);
			}
		} else {
			System.out.println("For best results, please run this from the command line.");
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();
			for(int i=0; i<10000; i++) System.out.println();
		}
		System.out.println(gallow);
		
		// create boolean variable to track if player 2 has won
		boolean win = false;
		
		// create array for fill-in-the-blank values
		char[] secret = new char[letters.length];
		for (int i = 0; i < secret.length; i++) {
			// initialize all values to underscores
			secret[i] = '_';
		}
		
		// game loop - keep asking for guesses until either the man is dead or the player has won
		while (gallow.isAlive() && win == false) {
			// set default value of win to true
			win = true;
			// print updated fill-in-the-blank values with spaces in between
			System.out.print("Puzzle to solve: ");
			for (int i = 0; i < secret.length; i++) {
				System.out.print(secret[i]);
				System.out.print(' ');
			}
						
			// ask for user guess (take first character of input)
			System.out.print("\nPlease guess a letter: ");
			char guesschar = s.nextLine().strip().toUpperCase().charAt(0);
			System.out.println(guesschar);
			
			// check if guess is correct
			// initialize variable to track if the guess matches any letters in the secret word
			// (default value is false)
			boolean correct = false;
			// iterate through each character in the secret word array
			for (int i = 0; i < letters.length; i++) {
				// check if the guess matches the secret character
				if (guesschar == letters[i]) {
					// if the guess matches, update the correct variable to true
					correct = true;
					// and replace the underscore with the letter
					secret[i] = guesschar;
					}
				else {
					continue; 
				}
			}
			// if correct is still false, then none of the letters in the secret word match the guess
			// and the guess is incorrect
			if (correct == false) {
				// if the guess is wrong, hang the man by calling a gallows method
				gallow.hang();
				// print the gallows to show the updated man
				System.out.println(gallow);
			}
			
			for (int i = 0; i < secret.length; i++) {
				// check if the player has won
				// if there are any underscores in the fill-in-the-blank value, then
				// the user has not fully guessed the word and win is set to false
				if (secret[i] == '_') {
					win = false;
				// if win is never set to false (all underscores have been replaced), then
				// win is still set to the default value of true and the while loop is broken
				}
			}
		}
		// close the scanner object
		s.close();
		// print who won, by testing if the win variable is true or false
		if (win == true) {
			System.out.println("Success!  Player 2 wins!");
		}
		else { 
			System.out.println("Game over! Player 1 wins!");
		}
	}
}






