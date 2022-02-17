/* Thought Questions:
 * 
 * 1)
 * 
 * 
 * 2)
 * 
 * 
 */

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
	
	public Vector<Double> get() {
		
		Vector<Double> newSubset = new Vector<Double>();
		
		for(int p = 0; p<v.size(); p++) {
			long mask = 1<<p;
			if ((mask & counter) > 0) newSubset.add(v.get(p));
		}
		
		return newSubset;
		
		/*
		// create vector of bits from the long subset
		Vector<Integer> bitSubset = new Vector<Integer>();
		int bitLength = (int) (Math.log(counter)/Math.log(2));
		for (int bitNum = 0; bitNum < bitLength; bitNum++) {
			int bit = (int) counter & (1<<bitNum);
			bitSubset.add(bit);
		}
		
		// create subset of values using bit vector
		// if bit is 1, add the corresponding value to the subset vector
		Vector<Double> result = new Vector<Double>();
		for (int i = 0; i < bitSubset.size(); i++) {
			if (bitSubset.get(i) == 1) result.add(v.get(i));
		}
		
		return result;
		*/
	}
	
	public Vector<Double> next() {
		subset = get();
		counter++;
		return subset;
	}
	
	public void printSubset() {
		//System.out.println(counter);
		for(Double d : subset) {
			System.out.print(d + " ");
		}
		System.out.println();
	}
}
