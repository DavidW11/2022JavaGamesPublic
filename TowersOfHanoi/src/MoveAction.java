import java.util.LinkedList;
import java.util.Queue;
import objectdraw.*;

public class MoveAction {
	
	private Queue<Move> auto;
	private int numBlocks = 0;
	private Pole startPole;
	private Pole endPole;
	private Pole auxPole;
	
	public MoveAction(int n, Pole startPole, Pole endPole, Pole auxPole) {
		numBlocks = n;
		this.startPole = startPole;
		this.endPole = endPole;
		this.auxPole = auxPole;
		auto = new LinkedList<Move>();
		solve(numBlocks, startPole, endPole, auxPole);
	}
	
	
	public void solve(int n, Pole startPole, Pole endPole, Pole auxPole) {
		if (n == 0) {
	        return;
	    }
	    solve(n - 1, startPole, auxPole, endPole);
	    Move m = new Move(startPole, endPole); 
	    auto.add(m);
	    solve(n - 1, auxPole, endPole, startPole);
	}
	
	public Queue<Move> getAuto(){
		return auto;
	}
	
	public static void main(String[] args) {	
	}
}

