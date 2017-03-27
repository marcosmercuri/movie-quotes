package movies.com.moviequotes;


import android.util.Log;

import movies.com.moviequotes.services.Movie;
import movies.com.moviequotes.services.QuotesConnector;
import movies.com.moviequotes.services.RandomMovie;
import movies.com.moviequotes.services.RandomMovieConnector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteInteractorImpl implements QuoteInteractor {
    private RandomMovieConnector randomMovieConnector;
    private QuotesConnector quotesConnector;
    private String accessToken;

    public QuoteInteractorImpl(RandomMovieConnector randomMovieConnector, QuotesConnector quotesConnector, String accessToken) {
        this.randomMovieConnector = randomMovieConnector;
        this.quotesConnector = quotesConnector;
        this.accessToken = accessToken;
    }

    @Override
    public void getRandomQuote(QuoteFetchedListener listener) {
        Call<RandomMovie> randomMovieCall = randomMovieConnector.findRandomMovie();
        randomMovieCall.enqueue(new RandomMovieCallback(listener));
    }

    private class RandomMovieCallback implements Callback<RandomMovie> {
        private final QuoteFetchedListener listener;

        public RandomMovieCallback(QuoteFetchedListener listener) {
            this.listener = listener;
        }

        @Override
        public void onResponse(Call<RandomMovie> call, Response<RandomMovie> response) {
            if (response.isSuccessful()) {
                Call<Movie> movieCall = quotesConnector.getMovie(response.body().getImdbID(), accessToken, 1);
                movieCall.enqueue(new MovieCallback(listener));
            } else {
                // error response, no access to resource?
                // TODO Call view.showError
                Log.e("fail", "problem a");
            }
        }

        @Override
        public void onFailure(Call<RandomMovie> call, Throwable t) {
            // TODO Call view.showError
            Log.e("fail", "problem b");
        }
    }

    private class MovieCallback implements Callback<Movie> {
        private final QuoteFetchedListener listener;

        public MovieCallback(QuoteFetchedListener listener) {
            this.listener = listener;
        }

        @Override
        public void onResponse(Call<Movie> call, Response<Movie> response) {
            if (response.isSuccessful()) {
                Movie body = response.body();
                Movie.InnerMovie innerMovie = body.getData().getMovies().get(0);
                Movie.InnerQuote innerQuote = innerMovie.getQuotes().get(0);
                listener.onSuccess(new Quote(innerMovie.getTitle(), innerQuote.getQuote()));
                Log.i("sucess", "esito");
            } else {
                // error response, no access to resource?
                // TODO Call view.showError
                Log.e("fail", "problem c");
            }
        }

        @Override
        public void onFailure(Call<Movie> call, Throwable t) {
            // TODO Call view.showError
            Log.e("fail", "problem d");
        }
    }

}
