package movies.com.moviequotes;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import movies.com.moviequotes.services.FirebaseDatabaseConnector;
import movies.com.moviequotes.services.DataBaseMovie;
import movies.com.moviequotes.services.Quote;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteInteractorImpl implements QuoteInteractor {
    private final FirebaseDatabaseConnector firebaseDatabaseConnector;

    public QuoteInteractorImpl(FirebaseDatabaseConnector firebaseDatabaseConnector) {
        this.firebaseDatabaseConnector = firebaseDatabaseConnector;
    }

    @Override
    public void getRandomQuote(QuoteFetchedListener listener) {
        Log.i("rest", "calling first step");
        Call<ResponseBody> shallowMovieElements = firebaseDatabaseConnector.getShallowMovieElements();
        shallowMovieElements.enqueue(new FirstStepCallback(listener));
    }

    private class FirstStepCallback implements Callback<ResponseBody> {
        private final QuoteFetchedListener listener;

        public FirstStepCallback(QuoteFetchedListener listener) {
            this.listener = listener;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Log.e("rest", "first step response");
            if (response.isSuccessful()) {
                JSONObject jsonObject = getResponse(response);
                int randomIndex = new Random().nextInt(jsonObject.length());
                Call<DataBaseMovie> movieByIndex = firebaseDatabaseConnector.getMovieByIndex(randomIndex);
                Log.e("rest", "calling second step");
                movieByIndex.enqueue(new SecondStepCallback(listener));
            } else {
                // error response, no access to resource?
                // TODO Call view.showError
                Log.e("fail", "problem a");
            }
        }

        private JSONObject getResponse(Response<ResponseBody> response) {
            try {
                return new JSONObject(response.body().string());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("fail", "problem a");
        }
    }

    private class SecondStepCallback implements Callback<DataBaseMovie> {
        private final QuoteFetchedListener fetchedListener;

        public SecondStepCallback(QuoteFetchedListener fetchedListener) {
            this.fetchedListener = fetchedListener;
        }

        @Override
        public void onResponse(Call<DataBaseMovie> call, Response<DataBaseMovie> response) {
            Log.e("rest", "second step response");
            if (response.isSuccessful()) {
                DataBaseMovie movie = response.body();
                List<Quote> quotes = movie.getQuotes();
                int randomIndex = new Random().nextInt(quotes.size());
                fetchedListener.onSuccess(new MovieQuote(movie.getFilm(), quotes.get(randomIndex)));
                Log.i("sucess", "esito");
            } else {
                // error response, no access to resource?
                // TODO Call view.showError
                Log.e("fail", "problem c");
            }
        }

        @Override
        public void onFailure(Call<DataBaseMovie> call, Throwable t) {
            Log.e("fail", "problem za");
        }
    }
}
