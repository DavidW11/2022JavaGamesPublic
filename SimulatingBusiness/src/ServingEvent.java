import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ServingEvent extends Event {
	
	private Teller teller;
	private int servingTime;
	private ArrivalEvent ae;
	private static int waitingTime;
	
	public ServingEvent(Teller t, int servingTime, ArrivalEvent ae) {
		teller = t;
		this.servingTime = servingTime;
		this.ae = ae;
	}
	
	public void process( PriorityQueue<Event> events, Queue<ArrivalEvent> customers, ArrayList<Teller> tellers, boolean isSingle) {
		System.out.println(this);
		
		FinishEvent fe = new FinishEvent(this);
		events.add(fe);
		
		// increment points
		waitingTime += (servingTime-ae.getTime());
	}
	
	public int getTime() {
		return servingTime;
	}
	
	public int getFinishTime() {
		return getTime() + ae.getServiceTime();
	}
	
	public int getCounter() {
		return ae.getCounter();
	}
	
	public Teller getTeller() {
		return teller;
	}
	
	public static int getAvgWaitingTime() {
		return waitingTime;
	}
	public static void resetTime() {
		waitingTime = 0;
	}
	
	public String toString() {
		return teller.toString() + " started serving Customer" + ae.getCounter() + " at time " + getTime() + 
				" (waited " + (servingTime-ae.getTime()) + "s)";
 	}
}
