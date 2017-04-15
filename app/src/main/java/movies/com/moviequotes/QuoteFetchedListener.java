package movies.com.moviequotes;


interface QuoteFetchedListener {
    void onError(String error);
    void onSuccess(MovieQuote quote);
}
