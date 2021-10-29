/*
Thought Questions:

3.4) 
The setSize() method can be used to add a certain number of null objects at the end of a vector, 
if the new size is greater than the old size,
or remove a certain number of elements at the end of a vector,
if the new size is smaller than the old size.

This can be useful, in order to iterate through or access elements of a vector without being 
completely sure that elements at that index have already been added, and without manually 
adding null objects. Otherwise, the compiler would throw an Index Out of Bound Exception. 
Setting an array of null objects also allows elements to be added out of consecutive order,
again, without having to manually add null objects between each non-null element.


3.6)
(See BitVector.java class)
There are multiple advantages to having a vector that holds one data type, such as BitVector.
There is no need to type cast elements in the string from Object to Boolean when
they are accessed. This also allows methods specified to certain data types.
Telling the compiler the data type also decreases runtime. 


3.8)
The array starts at 0 length. 
The length is then increases to 1 and 0 elements are copied.
The length is then increases to 2 and 1 element is copied.
The length then increases to 3 and 2 elements are copied.
The length then increases to 4 and 3 elements are copied... etc.

Thus, for an array with n elements, the total number of elements that are copied over is
1 + 2 + 3 + 4 + ... + (n-1)
the sum of this series is the sum of the first and last terms multiplied by the number of
pairs of numbers that make that sum
the sum of the first and last terms is (n-1) + 1
the number of pairs is (n-1)/2

= ((n-1)+1)*(n-1)/2
= n(n-1)/2
= (n^2 - n)/2

For large values of n, only the n^2 term matters so the sum is approximately n^2.

*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ReverseOrder {

	public static void main(String args[]){
		
	// check if an argument was entered
	if (args.length > 0) {
		File f = new File(args[0]);
		Scanner s;

		try {

			s = new Scanner(f);

			// initialize 2-D array
			// each array in the ArrayList represents a line in the file
			ArrayList<String[]> words = new ArrayList<String[]>();

			while(s.hasNext()){
				// split each line into an array of strings
				// add array to ArrayList
				words.add(s.nextLine().split(" "));
			}
	
			// iterate through each line of the file backwards
			for(int i = words.size()-1; i >= 0; i--){
				
				String[] line = words.get(i);
				String reverse = "";
				
				// iterate through each word of the line backwards
				for (int a = line.length -1; a >= 0; a--) {
					// concatenate to string in reverse order
					reverse += line[a] + " ";
				}
				// print string
				System.out.println(reverse);
			}
			

		} catch ( FileNotFoundException e) {
			System.out.println("File not found");
		} catch (Exception e){
			System.out.println("Something else went wrong.");
		}
	}

	else {
		System.out.println("Please Enter a File");
	}
	}
}
