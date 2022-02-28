
public class Move {

	Pole startPole;
	Pole endPole;
	
	public Move(Pole startPole, Pole endPole) {
		this.startPole = startPole;
		this.endPole = endPole;
	}
	
	public Pole getStart() {
		return startPole;
	}
	
	public Pole getEnd() { 
		return endPole;
	}
	
	public String toString() {
		return startPole.toString() + " to " + endPole.toString();
	}
	
	public boolean equals(Move m) {
		return startPole.equals(m.startPole) && endPole.equals(m.endPole);
	}
}
