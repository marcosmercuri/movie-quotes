package movies.com.moviequotes;


import android.util.Log;

public class QuotePresenterImpl implements QuotePresenter, QuoteFetchedListener {
    private QuoteView quoteView;
    private final QuoteInteractor interactor;

    public QuotePresenterImpl(QuoteView quoteView, QuoteInteractor interactor) {
        this.quoteView = quoteView;
        this.interactor = interactor;
    }

    @Override
    public void getRandomQuote() {
        Log.e("presenter", "calling random quote");
        interactor.getRandomQuote(this);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess(MovieQuote quote) {
        quoteView.showQuote(quote);
    }
}
