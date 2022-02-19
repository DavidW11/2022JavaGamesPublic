/* Thought Questions:
 * 
 * 1)
 * Best Solution: 1 2 3 7 8 9 14 15
 * (and 4 5 6 10 11 12 13)
 * 
 * 2)
 * On each call of the get() method in SubsetIterator, I would use Math.random() to 
 * randomly generate a counter between 0 and 2^n - 1. I would keep the same logic that
 * checks for the subset closest to h/2, but instead of a while(si.haxNext()) loop, I would
 * check if a variable time, measured in milliseconds, is less than 1000.
 * For each iteration of the loop, update the time using System.currentTimeMillis().
 * When the loop ends, print the closest subset.
 * 
 */

import structure5.Vector;

public class TwoTowers {
	
	private final static int N = 15;
	private static Vector<Double> tower;
	
	public static void main(String[] args) {
		
		// vector of block heights from sqrt(1) to sqrt(N)
		tower = new Vector<Double>();
		for(int i = 1; i<=N; i++) {
			tower.add(Math.sqrt(i));
		}
		
		SubsetIterator si = new SubsetIterator(tower);
		
		// find the smallest difference between the height of a subset and total height/2
		double smallestDiff = sum(tower);
		// remember the subset that gets the closest
		Vector<Double> towerA = new Vector<Double>();
		while (si.hasNext()) {
			Vector<Double> nextSub = si.next();
			if( diff(nextSub) < smallestDiff ) {
				smallestDiff = diff(nextSub);
				towerA = nextSub;
			}
		}
		
		// print results
		System.out.println("Best Towers: ");
		printTower(towerA);
		printTower(otherTower(towerA, tower));
		
		System.out.print("Height Difference: ");
		System.out.println(smallestDiff*2);
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
	
	// forms the second tower based on the first tower
	public static Vector<Double> otherTower(Vector<Double> firstTower, Vector<Double> totalTower) {
		Vector<Double> otherTower = new Vector<Double>();
		// if an item is not in the first tower but in the total tower, put in the second tower
		for(Double d : totalTower) {
			boolean inList = false;
			for(Double i : firstTower) {
				if(d==i) {
					inList = true;
					break;
				}
			}
			if(inList == false) {
				otherTower.add(d);
			}
		}
		return otherTower;
	}
}
