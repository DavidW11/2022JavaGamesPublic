import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Event implements Comparable<Event>{
	
	public abstract int getTime();
	
	public abstract void process( PriorityQueue<Event> events, Queue<ArrivalEvent> customers, ArrayList<Teller> tellers, boolean isSingle );
	
	public int compareTo(Event e) {
		return getTime()-e.getTime();
	}
}