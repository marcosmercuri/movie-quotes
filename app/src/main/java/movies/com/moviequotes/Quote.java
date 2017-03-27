package movies.com.moviequotes;

import java.util.List;

public class Quote {
    private String movie;
    private List<String> textLines;

    public Quote(String movie, List<String> textLines) {
        this.movie = movie;
        this.textLines = textLines;
    }

    public String getMovie() {
        return movie;
    }

    public List<String> getTextLines() {
        return textLines;
    }
}
