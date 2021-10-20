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
 * and the game continues with the puzzle set as this randomword
 * 
 * If the user selects 2 player, the game continues as normal
 * 
 * 
 * 2. 
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
 * The Rect class also abst meth
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
	protected int playerNum = 0;
	
	// GUI Elements
	protected Text label;
	protected Text buttonText;
	protected FramedRect button;
	protected Text puzzle;
	protected Gallows gallows;
	protected FramedRect player1;
	protected Text player1Text;
	protected FramedRect player2;
	protected Text player2Text;
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
            buttonText.move(buttonText.getWidth()/-2.0, buttonText.getHeight()/-2.0);
            buttonText.hide();
            
            puzzle = new Text("Puzzle to Solve: ", WINDOW_SIZE/2, WINDOW_SIZE - PUZZLE_OFFSET, canvas);
            puzzle.setFontSize(30);
    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    		puzzle.hide();
    		
    		
    		
    		
    		player1 = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
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
    	if(setup && player1.isHidden() && player2.isHidden()) {
    		if(word.isEmpty()) puzzle.setText("Puzzle to Solve: ");
    		char letter = e.getKeyChar();
    		if (Character.isLetter(letter)) {
	    		/* TODO: Update the puzzle text with the letter
	    		 * that was just entered.
	    		 */
    			word += letter;
    			puzzle.setText(puzzle.getText() + "_ ");
    			
	    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
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
    			word = word.substring(0,(word.length()-1));
    			puzzle.setText(puzzle.getText().substring(0, puzzle.getText().length()-2));
    			
    			puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    			
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
    		boolean correct = false;
    		
    		for (int i = 0; i < letters.length; i++) {
    			if (letters[i] == guessedLetter) {
    				correct = true;
    				lettersRemaining --;
    			}
    			else {
    				continue;
    			}
    		}
    		
    		if (correct == false) {
    			gallows.hang();
    		}
    		else {
    			updateGuessWord(guessedLetter);
    		}
    		
    		if (lettersRemaining == 0) {
    			gallows.clear();
    			label.setText("Congratulations! You solve the puzzle. Enter a new word.");

				newGame.show();
				newGameText.show();

				gallows.clear();
				label.setText("New Game?");
				puzzle.hide();
    		}
    		
    		if (gallows.isAlive() == false) {
    			
    			String wordlabel = "Puzzle to Solve: ";
    			for (int i = 1; i <= word.length(); i++) {
    				wordlabel = wordlabel + word.substring(i-1, i) + " ";
    				
    			}
    			puzzle.setText(wordlabel);
    			label.setText("Game Over! Player 1 wins.");

				newGame.show();
				newGameText.show();

				gallows.clear();
				label.setText("New Game?");
				puzzle.hide();
    		}
    		
    	}
    }
    
    public void onMousePress(Location point) {
		if (newGame.contains(point) && !newGame.isHidden()) {
			
			System.out.println("testing");
			
			newGame.hide();
			newGameText.hide();
			gallows.erase();

			puzzle.setText("Puzzle to Solve: ");
			puzzle.hide();
			label.setText("1 or 2 Player?");
			word = "";

			player1.show();
			player2.show();
			player1Text.show();
			player2Text.show();			

			setup = true;
		}

    	if (player1.contains(point) && !player1.isHidden()) {
			player1.hide();
			player2.hide();
    		player1Text.hide();
    		player2Text.hide();

			puzzle.show();

    		RandomWord2 randomWord = new RandomWord2();
			word = randomWord.chooseRandom();
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

    		label.setText("Player 2, please type a key to guess a letter.");
    		
    		gallows = new Gallows(WINDOW_SIZE/3.0, WINDOW_SIZE*2.0/3, canvas);
    		
    		setup = false;
    	}
    	
    	if (player2.contains(point) && !player2.isHidden()) {
    		puzzle.show();
    		label.setText("Player " + getPlayerNum() + ", please enter a word.");
    		player1.hide();
    		player2.hide();
    		player1Text.hide();
    		player2Text.hide();
    	}
    	
    	if (button.contains(point) && !button.isHidden() && ! word.isEmpty()) {
    		/* TODO:  Add logic to exit setup mode and
    		 * start game play
    		 */
    		letters = word.toCharArray();
    		puzzleLetters = new char[word.length()];
    		for (int i = 0; i < puzzleLetters.length; i++) {
    			puzzleLetters[i] = '_';
    		}
    		
    		lettersRemaining = letters.length;
    		
    		button.hide();
    		buttonText.hide();
    		label.setText("Player 2, please type a key to guess a letter.");
    		
    		gallows = new Gallows(WINDOW_SIZE/3.0, WINDOW_SIZE*2.0/3, canvas);
    		
    		setup = false;
    	}
    }
    
    public int getPlayerNum() {
    	return playerNum + 1;
    }
	
  
    public void updateGuessWord(char guessedLetter) {
    	/* TODO:  Add logic to update the guessed word.
    	 * Also include logic to test if the puzzle has
    	 * been solved (allowing the user to enter a new
    	 * word for their opponent if the puzzle is complete). 
    	 */	
    	
    	for (int i = 0; i < letters.length; i++) {
			if (letters[i] == guessedLetter) {
				puzzleLetters[i] = guessedLetter;
			}
		}
    	
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
