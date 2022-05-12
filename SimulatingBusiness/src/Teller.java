import java.util.LinkedList;
import java.util.Queue;

public class Teller {
	
	private int counter;
	private Queue<ArrivalEvent> customerLine;
	private boolean occupied;
	
	public Teller(int counter) {
		this.counter = counter;
		customerLine = new LinkedList<ArrivalEvent>();
	}
	
	public Queue<ArrivalEvent> getLine() {
		return customerLine;
	}
	
	public void toggleOccupied() {
		occupied = !occupied;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public String toString() {
		return "Teller" + counter;
	}
}
