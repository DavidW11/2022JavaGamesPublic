import structure5.*;

public class SubsetIterator extends AbstractIterator<Vector<Double>>{

	private Vector<Double> v;
	private Vector<Double> subset;
	private long counter;
	
	public SubsetIterator(Vector<Double> v) {
		this.v = v;
		counter = 0;
	}
	
	public void reset() {
		counter = 0;
	}
	
	public boolean hasNext() {
		return counter >= 0 && counter <= (Math.pow(2, v.size()) - 1);
	}
	
	// gets subset
	public Vector<Double> get() {
		Vector<Double> newSubset = new Vector<Double>();
		
		for(int p = 0; p<v.size(); p++) {
			long mask = 1<<p;
			if ((mask & counter) > 0) newSubset.add(v.get(p));
		}
		
		return newSubset;
	}
	
	// returns next subset
	public Vector<Double> next() {
		subset = get();
		counter++;
		return subset;
	}
}
