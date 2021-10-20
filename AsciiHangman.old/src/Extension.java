/*
 
Extensions:

1. Displays all incorrect guesses in alphabetical order
2. Asks for new guess when user enters
	a. any guess that was alredy guessed before
	b. any guess more than one character long
3. Single player mode - 
	the user is prompted to choose either 1 or 2 player mode before the game starts
	if the user chooses 1 player - the RandomWord class is called to choose a random word from the text file "words.txt"
4. Displays correct guess when man dies

// only comments for changed code

*/


import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class Extension {
	// incorrect array for all incorrect letters
	protected char[] incorrect;
	// guessed array for all letters already guessed
	protected char[] guessed;
	// alphabet array that contains the alphabet
	protected char[] alphabet;
	
	public Extension() {
		// length is 26 for 26 letters in the alphabet
		incorrect = new char[26];
		// default for incorrect array is all empty spaces
		for (int i = 0; i < incorrect.length; i++) {
			incorrect[i] = ' ';
		}
		// same procedure for guessed array
		guessed = new char[26];
		for (int i = 0; i < guessed.length; i++) {
			guessed[i] = ' ';
		// create alphabet array
		alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		}
	}
	
	public String incorrectLetters(char g) {
		// returns incorrect letters (only called when g is incorrect)
		for (int i = 0; i < alphabet.length; i++) {
			// letters are in alphabetical order
			if (g == alphabet[i]) {
				incorrect[i] = g;
			}
		}
		// concatenate all (non white-space) items in the array into a printable string
		String s = "";
		for (char a: incorrect) {
			if (a != ' ') {
				s = s + String.valueOf(a) + " ";
			}
		}
		// return string of incorrect letters
		return s;
	}
	
	public boolean alreadyGuessed(String guess) {
		// determines if guess has already been guessed
		boolean alreadyGuessed = false;
		// iterate through guessed array
		for (char i:guessed) {
			if (guess.charAt(0) == i) {
				// set variable to true if the guess matches an item in the array
				alreadyGuessed = true;
			}
			else {
				continue;
			}
		}
		return alreadyGuessed;
	}
	
	public char invalidGuess(Scanner s) {
		// asks user for guess and 
		// determines if guess is invalid 
		// (if guess has already been guessed or is more than one character long)
		
		// prompt user for guess and turn uppercase
		String guess = s.nextLine().strip().toUpperCase();
		
		// prompt for new guess until a valid guess is entered
		while (guess.length() != 1 || this.alreadyGuessed(guess) == true) {
			System.out.print("Please enter a valid guess: ");
			guess = s.nextLine().strip().toUpperCase();
			
		}
		// turn into character
		char guesschar = guess.charAt(0);
		
		// add guess to guessed array (using alphabet)
		for (int i = 0; i < alphabet.length; i++) {
			if (guesschar == alphabet[i]) {
				guessed[i] = guesschar;
			}
		}
		
		// return the guess
		return guesschar;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to the ASCII Version of Hangman!");
		
		Scanner s = new Scanner(System.in);
		// diffent scanner for prompting user for single or double player
		Scanner p = new Scanner(System.in);
		char[] letters;
		Gallows gallow = new Gallows();
		// extension object
		Extension ext = new Extension();
		boolean win = false;
		// RandomWord object
		RandomWord random = new RandomWord();
		Console c = System.console();
		
		// ask for number of players
		System.out.print("1 or 2 players? ");
		int players = p.nextInt();
		// answer must be either 1 or 2
		while (!(0<players && players<3)) {
			System.out.print("1 or 2 players? ");
			players = p.nextInt();
		}
		
		String prompt = "Please enter a secret word: ";
		// if single player, call chooseRandom method from RandomWord class
		// chooses random word from "words.txt"
		if (players == 1) {
			letters = random.chooseRandom().toCharArray();
		}
		// if multiplayer, normal game
		else {
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();
			for(int i=0; i<10000; i++) System.out.println();
		}
		
		for(int i=0; i<letters.length; i++) {
			letters[i] = Character.toUpperCase(letters[i]);
		}
		
		
		System.out.println(gallow);
		
		char[] secret = new char[letters.length];
		System.out.print("Puzzle to solve: ");
		for (int i = 0; i < secret.length; i++) {
			secret[i] = '_';
			System.out.print(secret[i]);
			System.out.print(' ');
		}
		
		while (gallow.isAlive() && win == false) {
			
			win = true;
			
			System.out.print("\nPlease guess a letter: ");
			
			// call invalidGuess method to prompt user for guess
			// and rejects invalid guesses
			char guesschar = ext.invalidGuess(s);
			
			System.out.println(guesschar);
			
			boolean correct = false;
			for (int i = 0; i < letters.length; i++) {
				if (guesschar == letters[i]) {
					correct = true;
					secret[i] = guesschar;
					}
				else {
					continue; 
				}
			}
			if (correct == false) {
				gallow.hang();
				System.out.println(gallow);
				
				// displays incorrect guesses using incorrectLetters method
				System.out.print("Incorrect Guesses: ");
				System.out.println(ext.incorrectLetters(guesschar));
			}
			
			for (int i = 0; i < secret.length; i++) {
				System.out.print(secret[i]);
				System.out.print(' ');
				if (secret[i] == '_') {
					win = false;
				}
			}
		}
		s.close();
		p.close();
		if (win == true) {
			System.out.println("\nSuccess! Player 2 Wins! ");
		}
		else { 
			System.out.println("\nGame over! Player 1 wins!");
			// concatenate all characters in letters array into the secret word
			String secretword = "";
			for (int i = 0; i<letters.length; i++) {
				secretword = secretword + letters[i];
			}
			// print the secret word
			System.out.print("The word was: ");
			System.out.println(secretword);
		}
	}
}
	
	
	
