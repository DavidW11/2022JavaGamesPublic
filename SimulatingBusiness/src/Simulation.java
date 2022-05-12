/* Thought Questions:
 * 
 * 1)
 * The MultiQueue total time is generally longer. This makes sense because the MultiQueue generally produces
 * a longer average waiting time (see 2nd Thought Question), which in turn creates a longer total time.
 * And while the arrival time of the last customer also affects the total time, as the density of our simulation
 * increases, or in other words, as the number of waiting customers increase, this arrival time is more likely
 * to occur before other customers are done serving. Thus, because the total time depends on waiting time, 
 * the MultiQueue generally produces a longer total time, unless, by chance, the customers in the MultiQueue choose
 * the optimal teller lines, allowing for a shorter waiting time and a shorter total time.
 * 
 * 
 * 2)
 * The MultiQueue average waiting time is generally longer. This makes sense because in simple situation with
 * 2 teller and 3 customers, the first 2 customers will be served immediately as they arrive. The 3rd customer,
 * assuming that they arrive as the first 2 customers are being served, has the option to go into either line. 
 * In the case of a SingleQueue, the customer will always be served by the teller that finishes first.
 * However, in the case of  MultiQueue, the customer has a 50 percent chance of choosing the line with a longer 
 * service time, thus producing a longer waiting time than the SingleQueue. In a real store, the customer would
 * simply switch lines to the open teller, however, our simulation does not support this functionality. 
 * Even as the number of customers and teller increase, this situation is replicated for customers in the MultiQueue, 
 * which explains why MultiQueue more often produces a longer average waiting time. 
 * But by chance, if the customers choose the correct lines, then the MultiQueue 
 * will sometimes produces a lower waiting time. However, because the chance of this happening is lower,
 * the MultiQueue more often produces a longer waiting time and also a longer total time. 
 * 
 * 
 * 3)
 * Because this functionality would require removing customers from both the front and back of the lines,
 * a queue, which only supports removal from the front of the line, would no longer work.
 * I would use either an array list or a circularly linked list for easy access to both the front of the line,
 * in order to begin serving customers, and the end of the line, in order to transfer customers between lines.
 * 
 * 
 * 4)
 * While the average wait time for customers with shorter serving times would decrease, 
 * the wait time of customers with longer serving times would increase.
 * Thus, if there is an even distribution of serving times, the total average wait time would remain consistent.
 * However, if there are more customers with shorter waiting times, implementing this change would decrease
 * the average wait time, and vice versa for samples with a greater number of customers with long serving times.
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Simulation {
	
	public static void singleQueue(PriorityQueue<Event> events, ArrayList<Teller> tellers) {
		
		Queue<ArrivalEvent> customers = new LinkedList<ArrivalEvent>();
		
		while(!events.isEmpty()) {
			events.remove().process(events, customers, tellers, true);
		}
	}
	
	public static void multiQueue(PriorityQueue<Event> events, ArrayList<Teller> tellers) {
		
		while(!events.isEmpty()) {
			events.remove().process(events, null, tellers, false);
		}
	}

	public static void main(String[] args) {
		
		int customerNum = 40;
		int tellerNum = 5;
		
		PriorityQueue<Event> events = new PriorityQueue<Event>();
		PriorityQueue<Event> events2 = new PriorityQueue<Event>();
		
		for(int i = 1; i<=customerNum; i++) {
			ArrivalEvent ae = new ArrivalEvent(i);
			events.add( ae );
			events2.add( ae );
		}
		
		ArrayList<Teller> tellers = new ArrayList<Teller>();

		for(int i = 1; i<=tellerNum; i++) {
			tellers.add(new Teller(i));
		}
		
		//ServingEvent.resetTime();
		System.out.println("Single: ");
		singleQueue(events, tellers);
		System.out.println("Average Waiting Time: " + ServingEvent.getAvgWaitingTime()/customerNum);
		
		ServingEvent.resetTime();
		System.out.println();
		System.out.println("Multi: ");
		multiQueue(events2, tellers);
		System.out.println("Average Waiting Time: " + ServingEvent.getAvgWaitingTime()/customerNum);
		
	}
}
