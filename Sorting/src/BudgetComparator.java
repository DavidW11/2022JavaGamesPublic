import java.util.Comparator;

public class BudgetComparator implements Comparator<Movie>{
	
	public int compare(Movie m1, Movie m2) {
		return m1.compareByBudget(m2);
	}
}
