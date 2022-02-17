/* Thought Questions:
 * 
 * 1)
 * 
 * 
 * 2)
 * 
 * 
 */

import structure5.Vector;

public class TwoTowers {
	
	private final static int N = 15;
	private static Vector<Double> tower;
	
	public static void main(String[] args) {
		// vector of block heights from sqrt(1) to sqrt(N)
		tower = new Vector<Double>();
		for(int i = 1; i<N; i++) {
			tower.add(Math.sqrt(i));
		}
		
		SubsetIterator si = new SubsetIterator(tower);
		
		// create vector of subsets
		Vector<Vector<Double>> subsets= new Vector<Vector<Double>>();
		while (si.hasNext()) {
			subsets.add(si.next());
		}
		
		// find the smallest difference between the height of a subset and total height/2
		double smallestDiff = diff(subsets.get(0));
		// remember the subset that gets the closest
		Vector<Double> towerA = subsets.get(0);
		for(int i = 0; i<subsets.size(); i++) {
				if( diff(subsets.get(i)) < smallestDiff ) {
					smallestDiff = diff(subsets.get(i));
					towerA = subsets.get(i);
				}
		}
		
		// print results
		System.out.print("Best Tower: ");
		printTower(towerA);
		
		System.out.print("Height Difference: ");
		System.out.println(smallestDiff);
	}
	
	// finds sum of a vector
	public static double sum(Vector<Double> v) {
		double sum = 0;
		for(Double d:v) {
			sum+=d;
		}
		return sum;
	}
	
	// finds difference between height of subset and total height/2
	public static double diff(Vector<Double> v) {
		return Math.abs(sum(v) - sum(tower)/2);
	}
	
	// prints the areas of each block in the tower
	public static void printTower(Vector<Double> v) {
		for(Double d:v) {
			System.out.print(Math.round(Math.pow(d, 2)) + " ");
		}
		System.out.println();
	}
}
