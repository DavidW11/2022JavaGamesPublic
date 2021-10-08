import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;  

import java.util.Scanner; 

public class RandomWord {
	// array for all words in the file
	protected String[] WordList;
	
	public RandomWord() {
		// creates array of words form text file "words.txt"
		
		String wordstring = "";
		
		try {
			// read in lines for "words.txt" file
			File obj = new File("/Users/DWang23/eclipse-workspace/AsciiHangman/src/words.txt");
			Scanner reader = new Scanner(obj);
			// concatenate all words in the file into a string
			while (reader.hasNextLine()) {
				wordstring = wordstring + reader.nextLine();
			}
			reader.close();
		}
		// exception for if file is not found
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		// split string into an array
		WordList = wordstring.split(" ");
	}
	
	
	public String chooseRandom() {
		// return random word from the array
		Random rand = new Random();
		int i = rand.nextInt(WordList.length);
		String randomword = WordList[i];
		return randomword;
		
	}
	
	
	public static void main (String[] args) {
		// for testing purposes
		RandomWord test = new RandomWord();
		System.out.println(test.chooseRandom());
		
	}
	
}




