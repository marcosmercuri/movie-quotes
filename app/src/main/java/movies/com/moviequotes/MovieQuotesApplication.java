package movies.com.moviequotes;

import android.app.Application;

import movies.com.moviequotes.services.DaggerServicesComponent;
import movies.com.moviequotes.services.ServicesComponent;
import movies.com.moviequotes.services.ServicesModule;

public class MovieQuotesApplication extends Application {
    public ServicesComponent getServicesComponent(QuoteView quoteView) {
        return DaggerServicesComponent
                .builder()
                .servicesModule(new ServicesModule(quoteView))
                .build();
    }
}
