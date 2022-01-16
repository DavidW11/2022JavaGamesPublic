import java.util.ArrayList;

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

class Darwin {
	
	private static int numRounds = 100;
	private static int numCreatures = 10;

	/**
	* The array passed into main will include the arguments that 
	* appeared on the command line. For example, running "java 
	* Darwin Hop.txt Rover.txt" will call the main method with s 
	* being an array of two strings: "Hop.txt" and "Rover.txt". 
	*/
	public static void main(String s[]) {
		
		WorldMap.createWorldMap(10, 10);
    	World<Creature> w = new World<Creature>(10,10);
    	
    	//TODO change to random
    	Position pos = new Position(3,3);
    	int dir = 0;
    	
    	// TODO make creatures from String s[]
    	/*
		ArrayList<Creature> creatureList = new ArrayList<Creature>();
		
		for (String speciesFile : s) {
			Species newSpecies = new Species(speciesFile);
			
			//TODO change to numCreatures
			for (int i = 0; i < 1; i++) {
				creatureList.add( new Creature(newSpecies, w, pos, dir) );
			}
		}
		
		simulate(creatureList);
		*/
		
		Species testSpecies = new Species("Hop.txt");
		Creature test = new Creature(testSpecies, w, pos, dir);
		ArrayList<Creature> testing = new ArrayList<Creature>();
		testing.add(test);
		simulate(testing);
	}
	
	// TODO is it okay if everything is static?
	public static void simulate(ArrayList<Creature> creatures) {
		for (int rounds = 0; rounds < numRounds; rounds++) {
			for (Creature c : creatures) {
				c.takeOneTurn();
			}
			// TODO CHANGE BACK to 100
			WorldMap.pause(1000);
		}
	}
}







