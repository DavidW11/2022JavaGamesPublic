import structure5.Vector;

public class TwoTowers {
	
	public static void main(String[] args) {
		Vector<Double> tower = new Vector<Double>();
		tower.add(1.0);
		tower.add(2.0);
		tower.add(3.0);
		
		SubsetIterator si = new SubsetIterator(tower);
		while (si.hasNext()) {
			si.next();
			si.printSubset();
		}
	}
}
