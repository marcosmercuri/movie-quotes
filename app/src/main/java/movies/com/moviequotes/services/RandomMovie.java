package movies.com.moviequotes.services;

public class RandomMovie {
    private String imdbID;

    RandomMovie(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbID() {
        return imdbID;
    }
}
