package movies.com.moviequotes.services;

import javax.inject.Singleton;

import dagger.Component;
import movies.com.moviequotes.QuotePresenter;

@Singleton
@Component(modules = {ServicesModule.class})
public interface ServicesComponent {
    QuotePresenter getQuotePresenter();
}
