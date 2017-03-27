package movies.com.moviequotes.services;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomMovieConnector {
    @GET("random")
    Call<RandomMovie> findRandomMovie();
}
