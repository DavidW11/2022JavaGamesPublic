import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ArrivalEvent extends Event{
	
	private int arrivalTime;
	private int serviceTime;
	
	private int counter;
	
	public ArrivalEvent(int counter) {
		arrivalTime = (int) (Math.random() * 2600);
		serviceTime = (int) (Math.random() * 1000);
		this.counter = counter;
	}
	
	public void process( PriorityQueue<Event> events, Queue<ArrivalEvent> customers, ArrayList<Teller> tellers, boolean isSingle) {
		System.out.println(this);
		
		if(isSingle) {
			
			if (tellers.size()>0 && customers.size()==0) {
				ServingEvent se = new ServingEvent(tellers.remove(0), getTime(), this);
				events.add(se);
			}
			else {
				customers.add(this);
			}
			
		}
		else {
			
			Teller minTeller = tellers.get(0);
			int minSize = minTeller.getLine().size();
			
			for(Teller t : tellers) {
				int size = t.getLine().size();
				if (size<minSize) {
					minSize = size;
					minTeller = t;
				}
			}
			
			if(minSize==0) {
				ServingEvent se = new ServingEvent(minTeller, getTime(), this);
				events.add(se);
			}
			minTeller.getLine().add(this);
		}
	}
	
	public int getTime() {
		return arrivalTime;
	}
	
	public int getServiceTime() {
		return serviceTime;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public String toString() {
		return "Customer" + counter + " arrived at time " + arrivalTime + " (needs " + serviceTime + "s)";
	}
}
