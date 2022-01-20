/**
 * This class represents one creature on the board.
 * Each creature remembers its species, position, direction,
 * and the world in which it is living.
 * <p>
 * In addition, the Creature remembers the next instruction
 * out of its program to execute.
 * <p>
 * The creature is also repsonsible for making itself appear in
 * the WorldMap.  In fact, you should only update the WorldMap from
 * inside the Creature class. 
 */

public class Creature {
	
    /**
     * Create a creature of the given species, with the indicated
     * position and direction.  Note that we also pass in the
     * world-- remember this world, so that you can check what
     * is in front of the creature and to update the board
     * when the creature moves.
	 * @param species The species of the creature
	 * @param world The world in which the creature lives
	 * @param pos The position of the creature
	 * @param dir The direction the creature is facing
	 * @pre species, world, and pos must be non-null
	 * @pre pos must be within the bounds of world
	 * @pre dir must be one of: Position.NORTH, Position.SOUTH, Position.EAST
	 *                          or Positon.WEST
	 * @pre the world map must have been created
	 * @post creates a creature of species species in world world at position
	 *       pos facing direction dir
	 * @post initializes instance variables so that the creature knows what
	 *		 instruction to begin executing
	 * @post displays the creature on the world map
     */
	
	private Species species;
	private World<Creature> world;
	private Position pos;
	private int dir;
	private char firstChar;
	private String color;
	// keeps track of which step its on
	private int stepNum;
	
    public Creature(Species species, World<Creature> world, Position pos, int dir) {
    	this.species = species;
    	this.world = world;
    	this.pos = pos;
    	this.dir = dir;
    	firstChar = species.getName().charAt(0);
    	color = species.getColor();
    	stepNum = 1;
    	WorldMap.displaySquare(pos, firstChar, dir, color);

	}

    /**
     * Return the species of the creature.
     */
    public Species species() {
    	return species;
	}

    /**
     * Return the current direction of the creature.
     */
    public int direction() {
    	return dir;
    }

    /**
     * Return the position of the creature.
     */
    public Position position() {
    	return pos;
    }
    
    // helper method for clearing a creature
    private void erase(Position p) {
		WorldMap.displaySquare(p, ' ', dir, color);
		world.set(p,null); 
    }

    // helper method for displaying a creature
    private void show(Position p) {
		WorldMap.displaySquare(p, firstChar, dir, color); 
		world.set(p, this); 
    }
    
    /**
     * Execute steps from the Creature's program until 
     * a hop, left, right, or infect instruction is executed.
	 * @post Creature takes one turn's worth of instructions
	 * @post display is updated to reflect movement of this creature
	 *
     */
    public void takeOneTurn() {
    	
    	// keeps track of whether or not the creature's turn has ended
    	boolean inTurn = true;
    	
    	while (inTurn) {
    		Instruction instr = species.programStep(stepNum);
        	Position adjacent = pos.getAdjacent(dir);
        	
        	switch(instr.getOpcode()) {
        	
        	case Instruction.HOP :
        		if (world.inRange(adjacent)) {
        			
        			erase(pos);
        			
        			pos = pos.getAdjacent(dir);
        			
        			show(pos);
        		}
        		stepNum++;
    			inTurn = false;
        		break;
        		
        	case Instruction.LEFT :
        		
        		erase(pos);
        		
        		if (dir == 0) dir = 3;
        		else dir--;
        		
        		show(pos);
        		
        		stepNum++;
        		inTurn = false;
        		break;
        		
        	case Instruction.RIGHT : 
        		
        		erase(pos);
        		
        		if (dir == 3) dir = 0;
        		else dir++;
        		
        		show(pos);
        		
        		stepNum++;
        		inTurn = false; 	
        		break;
        	
        	case Instruction.INFECT :
        		if (world.inRange(adjacent) &&
        				world.get(adjacent) != null) {
        			
        			Creature victim = world.get(adjacent);
        			
        			victim.species = this.species;
        			victim.firstChar = this.firstChar;
        			victim.color = this.color;
        			victim.stepNum = 1;
        			
        			erase(adjacent);
        			world.set(adjacent, victim);
        			WorldMap.displaySquare(adjacent, firstChar, victim.dir, color);
        		}
        		stepNum++;
    			inTurn = false;
        		break;
        		
        	case Instruction.IFEMPTY :
        		if (world.inRange(adjacent) &&
        				world.get(adjacent) == null) {
        			stepNum = instr.getAddress();
        		}
        		else {
        			stepNum ++;
        		}
        		break;
        		
        	case Instruction.IFWALL :
        		if (!world.inRange(adjacent) &&
        				world.inRange(pos)) {
        			stepNum = instr.getAddress();
        		}
        		else {
        			stepNum++;
        		}
        		break;
        		
        	case Instruction.IFSAME :
        		if (world.inRange(adjacent) &&
        				world.get(adjacent) != null && 
        				world.get(adjacent).species.equals(this.species)) {
        			stepNum = instr.getAddress();
        		}
        		else {
        			stepNum++;
        		}
        		break;
        		
        	case Instruction.IFENEMY :
        		if (world.inRange(adjacent) &&
        				world.get(adjacent) != null &&
        				!world.get(adjacent).species.equals(this.species)) {
        			stepNum = instr.getAddress();
        		}
        		else {
        			stepNum++;
        		}
        		break;
        		
        	case Instruction.IFRANDOM :
        		double randNum = Math.random();
        		if (randNum < 0.5) {
        			stepNum = instr.getAddress();
        		}
        		else {
        			stepNum++;
        		}
        		break;
        	
        	case Instruction.GO :
        		stepNum = instr.getAddress();
        		break;
        	
        	// extensions
        		// iftwoenemy
        		// ifenemyright
        		// if enemyleft
        	case Instruction.IFTWOENEMY :
        		Position adjacentTwo = adjacent.getAdjacent(dir);
        		if (world.inRange(adjacentTwo) &&
        				world.get(adjacentTwo) != null &&
        				!world.get(adjacent).species.equals(species)) {
                	stepNum = instr.getAddress();
                }
                else {
                	stepNum++;
                }
        	
        	case Instruction.IFENEMYLEFT :
        		int leftdir;
        		if (dir == 0) leftdir = 3;
        		else leftdir = dir - 1;
        		// adjacent square to the left
        		Position leftadj = pos.getAdjacent(leftdir);
        		
        		if (world.inRange(leftadj) &&
        				world.get(leftadj) != null &&
        				!world.get(leftadj).species.equals(species)) {
        			stepNum = instr.getAddress();
        		}
        		else {
        			stepNum++;
        		}
        		break;
        	
        	case Instruction.IFENEMYRIGHT :
        		int rightdir;
        		if (dir == 3) rightdir = 0;
        		else rightdir = dir + 1;
        		// adjacent square to the right
        		Position rightadj = pos.getAdjacent(rightdir);
        		
        		if (world.inRange(rightadj) &&
        				world.get(rightadj) != null &&
        				!world.get(rightadj).species.equals(species)) {
        			stepNum = instr.getAddress();
        		}
        		else {
        			stepNum++;
        		}
        		break;
        		
            default:
        		System.out.println("BAD OPCODE");
        	}
        	// fix infect error
    	}
	}
}
