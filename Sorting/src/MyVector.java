import java.util.Comparator;
import structure5.*;

public class MyVector<E> extends structure5.Vector<E> {

	public MyVector() {
		super();
	}
	
	public void sort(Comparator<E> c) {
		// use selection sort algorithm
		// sort in descending order
		
		for(int j = 0; j < elementCount -1; j++) {
			
			// find largest index
			int largestIndex = j;
			for(int i = j+1; i < elementCount; i++) {
				
				// use comparator to compare objects
				if(c.compare(get(i), get(largestIndex)) > 0) {
					largestIndex = i;
				}
			}
			
			// swap largest value with the front value
			E temp = get(largestIndex);
			set(largestIndex, get(j));
			set(j, temp);
		}
	}
	
	public String toString() {
		// print ordered list
		
		String result = "";
		
		for(int i = 0; i < elementCount; i ++) {
			// before each element, print the element's number in the list
			// the object's toString method, implemented in Movies class
			result += (i+1) + ". " + get(i).toString() + "\n";
		}
		
		return result;
	}
}
