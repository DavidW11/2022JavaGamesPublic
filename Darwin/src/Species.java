/**
 * The individual creatures in the world are all representatives of some
 * species class and share certain common characteristics, such as the species
 * name and the program they execute. Rather than copy this information into
 * each creature, this data is recorded once as part of the description for
 * a species and then each creature can simply include the appropriate species
 * pointer as part of its internal data structure.
 * <p>
 * 
 * To encapsulate all of the operations operating on a species within this
 * abstraction, this provides a constructor that will read a file containing
 * the name of the creature and its program, as described in the earlier part
 * of this assignment. To make the folder structure more manageable, the
 * species files for each creature are stored in a subfolder named Creatures.
 * Thus, creating the Species for the file Hop.txt will causes the program to
 * read in "Creatures/Hop.txt".
 * 
 * <p>
 * 
 * Note: The instruction addresses start at one, not zero.
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Species {
	
	// list of instructions
	private ArrayList<Instruction> instr;
	private String name;
	private String color;
	
	/**
	 * Create a species for the given file. 
	 * @param fileName the name of the file containing the data for the species
	 * @throws FileNotFoundException 
	 * @pre fileName exists in the Creature subdirectory.
	 */
	public Species(String fileName) {
		
		instr = new ArrayList<Instruction>();
		
		try {
			// read in new file
			File obj = new File("Creatures" + java.io.File.separator + fileName);
			Scanner reader = new Scanner(obj);
		
			int counter = 0;
			// iterate through lines of file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				
				// stop reading text if line is blank
				if (line.equals("")) {
					break;
				}
				
				// set name
				if (counter == 0) {
					name = line;
				}
				// set color
				else if (counter == 1) {
					color = line;
				}
				// set instructions
				else {
					// split line into opcode and address
					String[] instructions = line.split(" ");
					// run opEncode helper method to turn into opcode int
					int opcode = opEncode(instructions[0]);
					// set address
					int address;
					// if no address, set address to 1
					if (instructions.length == 1) {
						address = 1;
					}
					else if (instructions.length == 2){
						address = Integer.valueOf(instructions[1]);
					}
					else {
						address = -1;
						System.out.println("BAD INSTRUCTION" + line);
					}
					
					// add new Instruction to list
					instr.add(new Instruction(opcode, address));
				}
				counter ++;
			}	
			reader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}

	/**
	 * Return the name of the species.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the color of the species.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Return the number of instructions in the program.
	 */
	public int programSize() {
		// subtract 2 to account for name and color
		return instr.size();
	}

	/**
	 * Return an instruction from the program. 
	 * @pre 1 <= i <= programSize().
	 * @post returns instruction i of the program.
	 */
	public Instruction programStep(int i) {
		// subtract 1 to get index
		return instr.get(i-1);
	}

	/**
	 * Return a String representation of the program.
	 */
	public String programToString() {
		String program = "";
		for (Instruction i : instr) {
			program += i.toString() + "\n";
		}
		return program;
	}
	
	// turn opcode string into int
	public int opEncode(String opString) {
		switch (opString) {
		case "hop" :
			return Instruction.HOP;
		case "left" :
			return Instruction.LEFT;
		case "right" :
			return Instruction.RIGHT;
		case "infect" :
			return Instruction.INFECT;
		case "ifempty" :
			return Instruction.IFEMPTY;
		case "ifwall" :
			return Instruction.IFWALL;
		case "ifsame" :
			return Instruction.IFSAME;
		case "ifenemy" :
			return Instruction.IFENEMY;
		case "ifrandom" :
			return Instruction.IFRANDOM;
		case "go" :
			return Instruction.GO;
		case "iftwoenemy" :
			return Instruction.IFTWOENEMY;
		case "ifeq" :
			return Instruction.IFEQ;
		case "inc" :
			return Instruction.INC;
		case "dec" :
			return Instruction.DEC;
		case "set" :
			return Instruction.SET;
			
		case "ifenemyleft" :
			return Instruction.IFENEMYLEFT;
		case "ifenemyright" :
			return Instruction.IFENEMYRIGHT;
		default : 
			return 16;
		}
	}
	
	// testing
	public static void main (String[] args) {
		Species test = new Species("Hop.txt");
	    System.out.println("name: " + test.getName());
	 	System.out.println("color: " + test.getColor());
		System.out.println("size: " + test.programSize());
		System.out.println("program string:" + "\n" + test.programToString());
		
		System.out.println("step 1: " + test.programStep(1));
		System.out.println("step 2: " + test.programStep(2));
	}
}
