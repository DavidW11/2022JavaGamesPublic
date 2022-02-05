import java.util.Comparator;

public class TRComparator implements Comparator<Movie>{
	
	public int compare(Movie m1, Movie m2) {
		return m1.compareByTR(m2);
	}
}
