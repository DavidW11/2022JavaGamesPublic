/* Extensions:
 * 
 * 1. Single and Double Player Modes
 * 
 * Before the game starts, the user is presented with two buttons: 1 player, or 2 player
 * (the label prompts the user to select one of the options)
 * (if the user types during this phase, nothing will happen)
 * 
 * If the user selects 1 player, a method from the RandomWord2 class I made is called, which 
 * reads in a random word form the words2.txt file
 * and the game continues with the puzzle set as this random word
 * 
 * If the user selects 2 player, the game continues as normal
 * 
 * The label for who won is changed based on how many players there are:
 * If there is one player, and the player loses, the label will show that 
 * the "computer" wins
 * If there are two players, and player 2 loses, the label will show that
 * "player 1" wins
 * 
 * 
 * 
 * 2. New Game Feature
 * 
 * If either the man dies or the user guesses the word, the gallows is replaced with a button
 * labeled "New Game"
 * 
 * If the user clicks on the button, they will be taken back to the player selection screen,
 * where they can choose the number of players and start a completely new game.
 * 
 * If they choose single player for a second time, the new random word will not be the same 
 * as the old word.
 * 
 * The word, puzzle, man, and labels are reset.
 * 
 * player number updates for each consecutive double player game (see 3rd extension)
 * 
 * 
 * 
 * 3. Label Name Updates for New Games
 * 
 * The player numbers for the guesser and the hanger will swap for consecutive 2 player games
 * 
 * For example, if player 1 enters a secret word and player 2 guesses for the first game,
 * then player 2 will enter a word and player 1 will guess for the next game
 * (if the user selects to play multiple double player games in a row)
 * the label will update to show these changes
 * 
 * if the user selects single player, then they will automatically be player 1
 * 
 */


/* Thought Questions:
 * 
 * 1. 
 * 
 * onMouseEnter(Location point)
 * This method is invoked when the mouse enters the WindowController's canvas, where point is the point that
 * the mouse enters. This can be used to track when and where the mouse enters the canvas.
 * 
 * onMouseExit(Location point)
 * This method is invoked when the mouse exits the WindowController's canvas, where point is the point that
 * the mouse exits. This can be used to track when and where the mouse exits the canvas.
 * 
 * onMouseMove(Location Point)
 * This method is invoked when the mouse is moved moved on the canvas (no buttons down), where point is the point 
 * that the mouse moves. This can be used to track where the mouse is being moved or its current position.
 * 
 * A simple program that can utilize these 3 methods is similar to the pointer pointer website
 * https://pointerpointer.com/
 * (I recommend you try it, its pretty fun)
 * which generates an image with a person pointing to the current location of your mouse pointer.
 * 
 * I would use the onMouseEnter method to track the point at which the mouse enters the screen, and generate
 * and image that points to that point.
 * I would use the onMouseExit method to track if the mouse has left the screen, in which case the screen should go black.
 * While the mouse is still moving on the canvas, I would use the onMouseMove method to track the location of the mouse
 * (every couple seconds or so) and generate a new image that points to that point.
 * 
 * 
 * 2.
 * 
 * I think that the objectdraw.Rect class is an abstract class that creates the shared attributes of a
 * a non-rounded rectangle (such as the basic shape, width, height, position, etc.)
 * in addition to behavior that is shared among all non - rounded rectangles (such as certain accessor methods)
 * 
 * The Rect class may also have abstract methods that the FilledRect and FramedRect both need but 
 * implement in different ways.
 * 
 * However, many of the attributes or methods mentioned above may also defined 
 * in the Rectangular class, which is extended by the Rect class.
 * the reason I specify that the Rect class is extended only by non-rounded rectangles is that there is also a class
 * called RoundedRect. Both RoundedRect and Rect extend the Rectangular class, which is most likely an even more general 
 * class that outlines attributes/functionality for all rectangles.
 * 
 * Both the FilledRect and FramedRect extend all of the attributes and functionality of Rect,
 * but they also add custom attributes and functionality outlined by the Resizable2DInterface
 * and the abstract methods in the Rect class. 
 * 
 * For example, the FilledRect class uses the rectangular attributes given by the Rect class, but also fills in
 * the inside of the rectangle, and implements methods in the Resizable2DInterface (moveTo, isHidden, sendToFront, etc.)
 * 
 * Similarly, the FramedRect implements all of the same methods, except that the FilledRect class only inks in the border
 * and not the entire rectangle.
 * 
 * 
 * 3.
 * 
 * The hide() method temporarily hides an object on the canvas from view,
 * (the object is still on the canvas, it is simply hidden)
 * while the removeFromCanvas() method permanently removes the object from the canvas
 * it is currently on.
 * 
 * The hide() method should be used when the programmer wants the object to be hidden from view
 * ** temporarily, but the object may still be used again.
 * 
 * The removeFromCanvas() method should be used when the programmer is absolutely sure that 
 * the object in question will never needed to be shown again.
 * 
 */



import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectdraw.*;
public class Hangman2 extends WindowController  implements KeyListener{

	protected String word = "";
	protected char[] letters;
	protected char[] puzzleLetters;
	protected boolean setup;
	protected int lettersRemaining;
	
	// variables to update player numbers (for extension #3)
	protected int playerNum = 0;
	protected int otherNum = 0;
	
	// variable to reflect if a player (2 players) wins or
	// the computer (single player) wins
	// this variable is changed to "computer" if the user selects single player
	// so that if the player loses, the label will display either "player 1" wins
	// or "the computer" wins
	protected String hangerName = "Player 1";
	
	// GUI Elements
	protected Text label;
	protected Text buttonText;
	protected FramedRect button;
	protected Text puzzle;
	protected Gallows gallows;
	// buttons for player number selection
	protected FramedRect player1;
	protected Text player1Text;
	protected FramedRect player2;
	protected Text player2Text;
	// button to restart game
	protected FramedRect newGame;
	protected Text newGameText;
	
	protected static final int WINDOW_SIZE = 600;
	protected static final int TEXT_OFFSET = 10;
	protected static final int PUZZLE_OFFSET = 120;
	protected static final int BUTTON_WIDTH = 200;
	protected static final int BUTTON_HEIGHT = 40;

	
    public void begin()
    {
            // Get ready to handle key focuses
            requestFocus();
            addKeyListener(this);
            canvas.addKeyListener(this);
            
            // Set up the GUI for Player to enter the target word.
            label = new Text("1 or 2 Player?", TEXT_OFFSET, TEXT_OFFSET, canvas);
            label.setFontSize(20);
            
            setup = true;
            
            button = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
            		WINDOW_SIZE/2 - BUTTON_HEIGHT,
            		BUTTON_WIDTH,
            		BUTTON_HEIGHT,
            		canvas);
            button.setColor(Color.RED);
            button.hide();
            
            buttonText = new Text("Click when finished.", 
            		button.getX() + BUTTON_WIDTH/2, 
            		button.getY() + BUTTON_HEIGHT/2, 
            		canvas);
            // center text inside of the button rectangle
            buttonText.move(buttonText.getWidth()/-2.0, buttonText.getHeight()/-2.0);
            buttonText.hide();
            
            // set up puzzle text
            puzzle = new Text("Puzzle to Solve: ", WINDOW_SIZE/2, WINDOW_SIZE - PUZZLE_OFFSET, canvas);
            puzzle.setFontSize(30);
            // move to middle of the screen
    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    		puzzle.hide();
    	
    		// set up player selection buttons
    		player1 = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
            		// place 1 player button above 2 player button
            		WINDOW_SIZE/2 - BUTTON_HEIGHT - 30,
            		BUTTON_WIDTH,
            		BUTTON_HEIGHT,
            		canvas);
            player1.setColor(Color.GREEN);
            player1Text = new Text("1 Player",
        		   	player1.getX() + BUTTON_WIDTH/2, 
        		   	player1.getY() + BUTTON_HEIGHT/2, 
        		   	canvas);
           	player1Text.move(player1Text.getWidth()/-2.0, player1Text.getHeight()/-2.0);
            
           	player2 = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
            		WINDOW_SIZE/2 - BUTTON_HEIGHT + 30,
            		BUTTON_WIDTH,
            		BUTTON_HEIGHT,
            		canvas);
            player2.setColor(Color.BLUE);
            player2Text = new Text("2 Player",
        		   	player2.getX() + BUTTON_WIDTH/2, 
        		   	player2.getY() + BUTTON_HEIGHT/2, 
        		   	canvas);
           	player2Text.move(player2Text.getWidth()/-2.0, player2Text.getHeight()/-2.0);
    		
           	// set up button for new game
			newGame = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
            		WINDOW_SIZE/2 - BUTTON_HEIGHT + 30,
            		BUTTON_WIDTH,
            		BUTTON_HEIGHT,
            		canvas);
            newGame.setColor(Color.GREEN);
            newGameText = new Text("Start New Game",
        		   	player2.getX() + BUTTON_WIDTH/2, 
        		   	player2.getY() + BUTTON_HEIGHT/2, 
        		   	canvas);
           	newGameText.move(newGameText.getWidth()/-2.0, newGameText.getHeight()/-2.0);
			newGame.hide();
			newGameText.hide();
    }
 
    
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	// add conditions that player 1 and 2 buttons are hidden
    	// so typing during player selection will not do anything
    	if(setup && player1.isHidden() && player2.isHidden()) {
    		if(word.isEmpty()) puzzle.setText("Puzzle to Solve: ");
    		char letter = e.getKeyChar();
    		if (Character.isLetter(letter)) {
	    		/* TODO: Update the puzzle text with the letter
	    		 * that was just entered.
	    		 */
    			// append letters to secret word
    			word += letter;
    			// add underscores to puzzle for each letter
    			puzzle.setText(puzzle.getText() + "_ ");
    			
    			// center puzzle
	    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
	    		// show button if a letter is entered
	    		if(word.length() == 1) {
	    			button.show();
	    			buttonText.show();
	    		}
    		} else if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && ! word.isEmpty()) {
    			/* TODO: Add logic to process the delete key having 
    			 * been pressed, adjusting the position of the puzzle
    			 * text accordingly.  Hide the "Click when finished" button 
    			 * if the word has been deleted entirely. 
    			 */
    			
    			// take substring of word --> AKA remove last letter of the word
    			// (substring excludes the character at right hand parameter)
    			word = word.substring(0,(word.length()-1));
    			// remove the last 2 characters in the puzzle (the empty space ' ' and the 
    			// underscore '_')
    			puzzle.setText(puzzle.getText().substring(0, puzzle.getText().length()-2));
    			// center puzzle
    			puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    			// hide button if the word is empty
    			if (word.isEmpty()) {
    				button.hide();
    				buttonText.hide();
    			}
    		}
    	} else if (gallows.isAlive()) {
    		char guessedLetter = e.getKeyChar();
    		/* TODO: Add logic to check if the letter
    		 * is in the word. Update the guess word
    		 * if the letter is found, otherwise hang
    		 * the man.
    		 */
    		
    		// after setup stage is over
    		// check if guess matches any letters in the secret word
    		boolean correct = false;
    		
    		for (int i = 0; i < letters.length; i++) {
    			if (letters[i] == guessedLetter) {
    				correct = true;
    				// decrement letters remaining if correct
    				lettersRemaining --;
    			}
    			else {
    				continue;
    			}
    		}
    		
    		// if wrong guess, hang the man
    		if (correct == false) {
    			gallows.hang();
    		}
    		// if correct guess, run update method
    		else {
    			updateGuessWord(guessedLetter);
    		}
    		
    		// win screen
    		if (lettersRemaining == 0) {
    			gallows.clear();
    			label.setText("Congratulations! You solved the puzzle. Enter a new word.");

    			// show button for new game
				newGame.show();
				newGameText.show();

				gallows.clear();
    		}
    		
    		// lose screen
    		if (gallows.isAlive() == false) {
    			
    			// fill in rest of word
    			String wordlabel = "Puzzle to Solve: ";
    			for (int i = 1; i <= word.length(); i++) {
    				wordlabel = wordlabel + word.substring(i-1, i) + " ";
    				
    			}
    			puzzle.setText(wordlabel);
    			label.setText("Game Over! " + hangerName + " wins.");
    			
    			// show button for new game
				newGame.show();
				newGameText.show();

				gallows.clear();
    		}
    		
    	}
    }
    
    public void onMousePress(Location point) {
    	
    	// new game button
		if (newGame.contains(point) && !newGame.isHidden()) {
			
			// hide all elements and 
			// return to player selection screen
			newGame.hide();
			newGameText.hide();
			// reset man
			gallows.erase();

			puzzle.setText("Puzzle to Solve: ");
			puzzle.hide();
			label.setText("1 or 2 Player?");
			word = "";

			
			player1.show();
			player2.show();
			player1Text.show();
			player2Text.show();			

			lettersRemaining = 0;
			// go back to setup stage
			setup = true;
		}

		// single player button
		else if (player1.contains(point) && !player1.isHidden()) {
			player1.hide();
			player2.hide();
    		player1Text.hide();
    		player2Text.hide();
    		
    		// set opponent name to computer
    		hangerName = "Computer";
    		// player number for single player mode is always 1
    		playerNum = 1;

			puzzle.show();
			
			// generate random word
    		RandomWord2 randomWord = new RandomWord2();
    		String oldword = word;
    		while (oldword == word) {
    			word = randomWord.chooseRandom();
    		}

    		// initialize variables
			letters = word.toCharArray();
    		puzzleLetters = new char[word.length()];
    		for (int i = 0; i < puzzleLetters.length; i++) {
    			puzzleLetters[i] = '_';
    		}

			String puzzletext = "Puzzle to Solve: ";
			for (int i = 0; i < letters.length; i++) {
				puzzletext = puzzletext + "_ ";
			}
			puzzle.setText(puzzletext);
			puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    		
    		lettersRemaining = letters.length;

    		label.setText("Player " + getPlayerNum("guesser") + ", please type a key to guess a letter.");
    		
    		gallows = new Gallows(WINDOW_SIZE/3.0, WINDOW_SIZE*2.0/3, canvas);
    		
    		setup = false;
    	}
    	
		else if (player2.contains(point) && !player2.isHidden()) {
			
			// swap player numbers
			// player number is initialized to 0, so on the first round through
			// it will be set to 2 by the else statement
			if (playerNum == 2) {
        		playerNum = 1;
        		otherNum = 2;
        	}
        	else {
        		playerNum = 2;
        		otherNum = 1;
        	}
			
    		puzzle.show();
    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    		hangerName = "Player " + getPlayerNum("hanger");
    		label.setText("Player " + getPlayerNum("hanger") + ", please enter a word.");
    		player1.hide();
    		player2.hide();
    		player1Text.hide();
    		player2Text.hide();
    		
    	}
    	
		// button to end setup mode (only shown in 2 player mode)
		else if (button.contains(point) && !button.isHidden() && ! word.isEmpty()) {
    		/* TODO:  Add logic to exit setup mode and
    		 * start game play
    		 */
			
			// initialize variables
    		letters = word.toCharArray();
    		puzzleLetters = new char[word.length()];
    		for (int i = 0; i < puzzleLetters.length; i++) {
    			puzzleLetters[i] = '_';
    		}
    		
    		lettersRemaining = letters.length;
    		
    		button.hide();
    		buttonText.hide();
    		label.setText("Player " + getPlayerNum("guesser") + ", please type a key to guess a letter.");
    		
    		gallows = new Gallows(WINDOW_SIZE/3.0, WINDOW_SIZE*2.0/3, canvas);
    		
    		setup = false;
    	}
    }
    
    public int getPlayerNum(String role) {
    	// returns the number of the guesser/hanger player depending on parameter
    	
    	if (role == "guesser") {
    		return playerNum;
    	}
    	else if (role == "hanger") {
    		return otherNum;
    	}
    	else {
    		// if code is bad
    		return (100);
    	}
    }
	
  
    public void updateGuessWord(char guessedLetter) {
    	/* TODO:  Add logic to update the guessed word.
    	 * Also include logic to test if the puzzle has
    	 * been solved (allowing the user to enter a new
    	 * word for their opponent if the puzzle is complete). 
    	 */	
    	
    	// replace underscore with correct letter
    	for (int i = 0; i < letters.length; i++) {
			if (letters[i] == guessedLetter) {
				puzzleLetters[i] = guessedLetter;
			}
		}
    	
    	// update puzzle text
    	String text = "Puzzle to Solve: ";
    	for (char i:puzzleLetters) {
    		i = Character.toUpperCase(i);
    		text = text + i + ' ';
    	}
    	
    	puzzle.setText(text);
    	puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());  	
    }
    
    public static void main(String[] args) { 
        new Hangman2().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
	
}
