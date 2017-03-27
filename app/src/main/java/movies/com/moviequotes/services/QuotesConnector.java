package movies.com.moviequotes.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuotesConnector {
    @GET("idIMDB")
    Call<Movie> getMovie(@Query("idIMDB") String imdbId,
                               @Query("token") String token,
                               @Query("quotes") int includeQuotes);
}
