/* Thought Questions: 
 * 
 * 1)
 * The RevComparator compare method will return the opposite (negative) value 
 * of the original comparator (passed into the constructor). 
 * In other words, the comparator reverses the comparison. 
 * When the MyVector of Integer objects is sorted using the RevComparator (with
 * an IntegerComparator passed in its constructor), the MyVector will be
 * sorted in the reverse order of how it would be sorted if an IntegerComparator
 * was used.
 * If the IntegerComparator would have sorted in ascending order, 
 * the RevComparator will sort in descending order, and vice versa.
 * 
 * 2)
 * I would add a private instance variable to our comparator of type Comparator.
 * Let's call this Comparator instance variable kingComparator.
 * In the compare method of our comparator, call the compare method of kingComparator,
 * which is safe because kingComparator is type Comparator — an interface that requires
 * a compare method.
 * In mutator methods inside of our comparator, set the kingComparator to other
 * comparators that implement Comparator, thus changing the functionality of our compare method
 * and the "mode" of our comparator.
 * This is because our compare method calls kingComparator's compare method, 
 * which is the compare method of whichever comparator the kingComparator is currently set to. 
 */

import java.util.Scanner;
import java.io.*;

public class FileReader {
	
	public static void main(String[] args) {
		
		File f = new File("Movie-Ratings.csv");
		Scanner s;
		
		MyVector<Movie> movies = new MyVector<Movie>();

		try {

			s = new Scanner(f);
			String[] line;

			while(s.hasNext()){
				// split each line by commas
				line = s.nextLine().split(",");
				
				// construct new Movie object using data in array
				// and add to array of movies
				
				String title = line[0];
				String genre = line[1];
				int tR = Integer.valueOf(line[2]);
				int aR = Integer.valueOf(line[3]);
				int budget = Integer.valueOf(line[4]);
				int releaseYear = Integer.valueOf(line[5]);
				
				movies.add( new Movie(title, genre, tR, aR, budget, releaseYear) );
				
			}

		} 
		catch ( FileNotFoundException e) {
			System.out.println("File not found");
		} 
		catch (Exception e){
			System.out.println("Something else went wrong.");
		}
		
		// unsorted list
		System.out.println("Unsorted:");
		System.out.println(movies);
		
		// sort by budget (descending)
		BudgetComparator bc = new BudgetComparator();
		movies.sort(bc);
		System.out.println("Sorted by Budget:");
		System.out.println(movies);
		
		// sort by Rotten Tomatoes rating (descending)
		TRComparator rc = new TRComparator();
		movies.sort(rc);
		System.out.println("Sorted By Rotten Tomatoes Rating:");
		System.out.println(movies);
	}
}
