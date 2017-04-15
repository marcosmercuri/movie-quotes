package movies.com.moviequotes.services;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FirebaseDatabaseConnector {
    @GET(".json?shallow=true")
    Call<ResponseBody> getShallowMovieElements();
    @GET("{index}.json")
    Call<DataBaseMovie> getMovieByIndex(@Path("index") Integer index);


}
