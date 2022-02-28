import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import objectdraw.*;

public class Animate extends ActiveObject{
	
	Pole p;
	
	public Animate(Pole p) {
		this.p = p;
	}
	
	public void run() {
		
		Stack<Block> aux = new Stack<Block>();
		
		while(!p.getPole().isEmpty()) {
			p.getPole().peek().changeColor();
			aux.push(p.getPole().pop());
			pause(500);
		}
		
		// regenerate old stack for reset functionality
		while(!aux.isEmpty()) {
			p.getPole().push(aux.pop());
		}
	}
	
}
