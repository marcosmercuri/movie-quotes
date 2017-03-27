package movies.com.moviequotes;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements QuoteView {
    @Bind(R.id.btnRandom)
    Button btnRandom;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private QuoteFragment quoteFragment;
    private QuotePresenter quotePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupInjection();
    }

    private void setupInjection() {
        MovieQuotesApplication application = (MovieQuotesApplication) getApplication();

        this.quotePresenter = application.getServicesComponent(this).getQuotePresenter();

        QuoteFragment fragment = (QuoteFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentQuote);
        fragment.setRetainInstance(true);
        quoteFragment = fragment;
    }

    @OnClick(R.id.btnRandom)
    public void handleClickOnRandom() {
        showProgress();
        generateRandomQuote();
    }

    public void showProgress() {
        Log.i("progress", "SHOW progress");

        // Block rotation
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }

        btnRandom.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        Log.i("progress", "HIDE progress");
        btnRandom.setEnabled(true);
        progressBar.setVisibility(View.GONE);

        // Allow rotation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    private void generateRandomQuote() {
        quotePresenter.getRandomQuote();
    }

    @Override
    public void showQuote(Quote quote) {
        quoteFragment.showQuote(quote);
        hideProgress();
    }
}
