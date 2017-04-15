package movies.com.moviequotes.services;


import java.util.List;

public class DataBaseMovie {
    private String film;
    private List<Quote> quotes;

    public DataBaseMovie(String film, List<Quote> quotes) {
        this.film = film;
        this.quotes = quotes;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
