package movies.com.moviequotes.services;

import java.util.List;

public class Movie {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private List<InnerMovie> movies;

        public List<InnerMovie> getMovies() {
            return movies;
        }

        public void setMovies(List<InnerMovie> movies) {
            this.movies = movies;
        }
    }

    public class InnerMovie {
        private String title;
        private List<InnerQuote> quotes;

        public List<InnerQuote> getQuotes() {
            return quotes;
        }

        public void setQuotes(List<InnerQuote> quotes) {
            this.quotes = quotes;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class InnerQuote {
        private List<String> quote;

        public List<String> getQuote() {
            return quote;
        }

        public void setQuote(List<String> quote) {
            this.quote = quote;
        }
    }
}
