import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FinishEvent extends Event{
	
	private ServingEvent se;
	
	public FinishEvent(ServingEvent se) {
		this.se = se;
	}
	
	public void process( PriorityQueue<Event> events, Queue<ArrivalEvent> customers, ArrayList<Teller> tellers, boolean isSingle) {
		System.out.println(this);
		
		
		if(isSingle) {
			tellers.add(se.getTeller());
			
			if(customers.size()>0) {
				events.add( new ServingEvent(tellers.get(0), getTime(), customers.remove()) );
			}
		}
		else {
			
			Teller t = se.getTeller();
			
			if(t.getLine().size()>0) {
				t.getLine().remove();
				
				if(t.getLine().size()>0) {
					events.add( new ServingEvent(t, getTime(), t.getLine().peek()) );
				}
			}
		}
	}
	
	public int getTime() {
		return se.getFinishTime();
	}
	
	public String toString() {
		return se.getTeller().toString() + " finished serving Customer" + se.getCounter() + " at time " + getTime();
 	}
}
