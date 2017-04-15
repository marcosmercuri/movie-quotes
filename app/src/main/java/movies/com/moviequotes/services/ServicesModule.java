package movies.com.moviequotes.services;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import movies.com.moviequotes.QuoteInteractor;
import movies.com.moviequotes.QuoteInteractorImpl;
import movies.com.moviequotes.QuotePresenter;
import movies.com.moviequotes.QuotePresenterImpl;
import movies.com.moviequotes.QuoteView;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServicesModule {
    private QuoteView quoteView;

    public ServicesModule(QuoteView quoteView) {
        this.quoteView = quoteView;
    }

    @Provides
    @Singleton
    QuotePresenter provideQuotePresenter(QuoteInteractor quoteInteractor) {
        return new QuotePresenterImpl(quoteView, quoteInteractor);
    }

    @Provides
    @Singleton
    QuoteInteractor provideQuoteInteractor(FirebaseDatabaseConnector firebaseDatabaseConnector) {
        return new QuoteInteractorImpl(firebaseDatabaseConnector);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    FirebaseDatabaseConnector provideFirebaseDatabaseConnector(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://movie-quotes-832b0.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(FirebaseDatabaseConnector.class);
    }

}
