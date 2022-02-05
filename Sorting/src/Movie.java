public class Movie {
	
	private String title;
	private String genre;
	private int tomatoesRating;
	private int audienceRating;
	private int budget;
	private int releaseYear;
	
	public Movie(String title, String genre, int tR, int aR, int budget, int year) {
		this.title = title;
		this.genre = genre;
		this.tomatoesRating = tR;
		this.audienceRating = aR;
		this.budget = budget;
		this.releaseYear = year;
	}
	
	public String toString() {
		return title + " | " + genre + " | " + tomatoesRating + "% | " 
				+ audienceRating + "% | $" + budget + " million | " + releaseYear;
	}
	
	// compare by Rotten Tomatoes Rating
	public int compareByTR(Movie other) {
		return tomatoesRating - other.tomatoesRating;
	}
	
	// compare by budget in millions
	public int compareByBudget(Movie other) {
		return budget - other.budget;
	}
}






