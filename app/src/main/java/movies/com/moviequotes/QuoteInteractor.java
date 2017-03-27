package movies.com.moviequotes;


public interface QuoteInteractor {
    void getRandomQuote(QuoteFetchedListener listener);
}
