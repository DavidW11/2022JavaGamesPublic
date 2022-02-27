import java.util.Stack;
import objectdraw.*;

public class Pole {
	
	private Stack<Block> pole;
	private FilledRect beam;
	private static final int BEAMHEIGHT = 400;
	private static final int BEAMWIDTH = 12;
	private String name;
	
	public Pole(Location base, DrawingCanvas canvas) {
		// base is bottom middle of the pole
		pole = new Stack<Block>();
		beam = new FilledRect(base.getX() - BEAMWIDTH/2, base.getY() - BEAMHEIGHT, BEAMWIDTH, BEAMHEIGHT, canvas);
	}
	
	// for testing
	public Pole(Location base, String name, DrawingCanvas canvas) {
		// base is bottom middle of the pole
		pole = new Stack<Block>();
		beam = new FilledRect(base.getX() - BEAMWIDTH/2, base.getY() - BEAMHEIGHT, BEAMWIDTH, BEAMHEIGHT, canvas);
		this.name = name;
	}
	
	// checks if block is smaller than the top block
	public boolean validMove(Block b) {
		if(pole.size() == 0 || b.getWidth() < pole.peek().getWidth()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// adds new block to stack and places block in the right spot
	public void add(Block b) {
		double y = getY() - (pole.size() * b.getHeight());
		double x = getX();
		b.moveTo(x, y);
		pole.push(b);
	}
	
	// removes block from stack
	public Block remove() {
		return pole.pop();
	}

	public void reset() {
		for (int i = pole.size(); i>0; i--) {
			pole.pop().erase();
		}
	}
	
	// x and y of beam correspond to the bottom middle point of the pole
	public double getX() {
		return beam.getX() + beam.getWidth()/2;
	}
	
	public double getY() {
		return beam.getY() + beam.getHeight();
	}
	
	public static int getWidth() {
		return BEAMWIDTH;
	}
	
	public Block getTop() {
		return pole.peek();
	}
	
	public boolean isEmpty() {
		return pole.size() == 0;
	}
	
	public FilledRect getBeam() {
		return beam;
	}
	
	public boolean win() {
		if(pole.size() == Block.getNumBlocks()) return true;
		else return false;
	}

	public String toString() {
		
		return pole.toString();
	}

}
