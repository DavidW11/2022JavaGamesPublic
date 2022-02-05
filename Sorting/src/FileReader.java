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
