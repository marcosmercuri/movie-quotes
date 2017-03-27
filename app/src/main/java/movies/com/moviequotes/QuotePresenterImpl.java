package movies.com.moviequotes;


public class QuotePresenterImpl implements QuotePresenter, QuoteFetchedListener {
    private QuoteView quoteView;
    private final QuoteInteractor interactor;

    public QuotePresenterImpl(QuoteView quoteView, QuoteInteractor interactor) {
        this.quoteView = quoteView;
        this.interactor = interactor;
    }

    @Override
    public void getRandomQuote() {
        interactor.getRandomQuote(this);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess(Quote quote) {
        quoteView.showQuote(quote);
    }
}
