/* Thought Questions:
 * 
 * 1) 
 * I would create a counter for the number of wins for each creature.
 * I would remove all WorldMap functionality, because a visual display is not needed. 
 * I would create a method in Darwin that checks if all creatures in the array are
 * the same species — in other words, if a species has won. This method runs after
 * every round in the simulation.
 * If a species has won, increment the appropriate win counter and
 * restart the game, running it another time.
 * Put a for loop around the Darwin code that starts the game, and restart the simulation 
 * a certain number of times — the more times, the more accurate the result.
 * Calculate the percentages of each species winning to determine which one wins more.
 * 
 * This simulation is not guaranteed to produce a result, because the game may go into
 * stalemate situations, such as when species are locked in position and no species are
 * infected. To prevent these situations, I will use a timer to stop the program if it goes on for
 * longer than a certain length.
 * 
 * 2)
 * Whereas a traditional compiler compiles bytecode into native machine code before run time, 
 * a JIT compiles bytecode at run time (or "just in time"), giving access to dynamic run time information.
 * A JIT also converts bytecode into internal representations called trees, which resemble machine code, 
 * and performs optimizations on these trees before they are converted to native code. 
 * A JIT optimizes these trees in multiple ways, such as 
 * a. inlining, which speeds up frequently executed method calls
 * b. local optimizations, which analyze and optimize small sections of code
 * c. control flow optimizations, which rearrange code paths to improve efficiency
 * d. global optimizations, which work on the entire method at once
 * e. native code generation, which translates trees into machine code and makes small 
 * changes according to the architecture.
 * 
 * In these ways, JIT optimizes the program and reduces run time.
 *  
 * 3)
 * I would generally agree with Edsger Dijkstra.
 * The go to statement provides more flexibility in writing code, which
 * may make certain programs easier to write, but at the same time, 
 * messier and more error-prone. 
 * In my experience writing go statements in creature text files, I found that 
 * the way each program jumped around the lines and the multitude of different
 * outcomes stringed together with go to statements made the code hard to read.
 * And while it was more intuitive to write, the code was harder to debug.
 * The code was hard to edit because I also had to constantly 
 * change the line numbers when adding in new lines.
 * Like Dijkstra said, the go to statement is "primitive" — the concept itself is
 * simple to understand, but that also means that it lacks complexity.
 * And while it may increase flexibility in writing a program, the go to 
 * statement itself lacks flexibility, which makes it easy to create a tangled
 * mess of go to statements.
 */

/**
* This class controls the simulation. The design is entirely up to
* you. You should include a main method that takes the array of
* species file names passed in and populates a world with species of 
* each type.
* <p>
* Be sure to call WorldMap.pause every time
* through the main simulation loop or else the simulation will be too fast
* and keyboard / mouse input will be slow. For example: 
* <pre>
*	public void simulate() {
*	for (int rounds = 0; rounds < numRounds; rounds++) {
*           giveEachCreatureOneTurn();
*           WorldMap.pause(100);
*         }
*	}
* </pre>
*/

import java.util.ArrayList;

class Darwin {
	
	// number of times the simulation runs
	private static final int numRounds = 1000000000;
	// set number of creatures per species
	private static final int numCreatures = 10;

	/**
	* The array passed into main will include the arguments that 
	* appeared on the command line. For example, running "java 
	* Darwin Hop.txt Rover.txt" will call the main method with s 
	* being an array of two strings: "Hop.txt" and "Rover.txt". 
	*/
	public static void main(String s[]) {
		
		WorldMap.createWorldMap(15, 15);
    	World<Creature> w = new World<Creature>(15,15);
    	
		ArrayList<Creature> creatureList = new ArrayList<Creature>();
		
		// testing:
		//ArrayList<String> testArray = new ArrayList<String>();
		//testArray.add("Rover.txt");

		// for each species passed in, create 10 creatures of that species
		for (String speciesFile : s) {
			Species newSpecies = new Species(speciesFile);

			for (int i = 0; i < numCreatures; i++) {
				// choose random direction
				int dir = (int) Math.random()*3;
				// choose random position
				Position randPos = new Position((int)(Math.random()*(w.width()-1)),
						   (int)(Math.random()*(w.height()-1)));
				
				creatureList.add( new Creature(newSpecies, w, randPos, dir) );
			}
		}

		simulate(creatureList);
		
    	/*
    	// testing:
    	
    	Position pos1 = new Position(0,0);
    	Position pos2 = new Position(0,0);
		int dir2 = 1;
		int dir1 = 3;
		Species testSpecies1 = new Species("Rover.txt");
		Species testSpecies2 = new Species("Flytrap.txt");
		Creature test1 = new Creature(testSpecies1, w, pos1, dir1);
		Creature test2 = new Creature(testSpecies1, w, pos2, dir2);
		
		ArrayList<Creature> testing = new ArrayList<Creature>();
		testing.add(test1);
		testing.add(test2);
		simulate(testing);
		*/
		
	}
	
	public static void simulate(ArrayList<Creature> creatures) {
		for (int rounds = 0; rounds < numRounds; rounds++) {
			for (Creature c : creatures) {
				c.takeOneTurn();
			}
		WorldMap.pause(100);
		}
	}
}







