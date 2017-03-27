package movies.com.moviequotes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuoteFragment extends Fragment {

    private static final String SAVE_INSTANCE_QUOTE_TEXT_KEY = "quoteText";
    private static final String SAVE_INSTANCE_MOVIE_TEXT_KEY = "movieText";
    @Bind(R.id.txtQuoteText)
    TextView txtQuoteText;
    @Bind(R.id.txtQuoteMovieTitle)
    TextView txtQuoteMovieTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState!=null && !savedInstanceState.isEmpty()) {
            txtQuoteText.setText((CharSequence) savedInstanceState.get(SAVE_INSTANCE_QUOTE_TEXT_KEY));
            txtQuoteMovieTitle.setText((CharSequence) savedInstanceState.get(SAVE_INSTANCE_MOVIE_TEXT_KEY));
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_INSTANCE_QUOTE_TEXT_KEY, txtQuoteText.getText().toString());
        outState.putString(SAVE_INSTANCE_MOVIE_TEXT_KEY, txtQuoteMovieTitle.getText().toString());
    }

    public void showQuote(Quote quote) {
        StringBuilder builder = new StringBuilder(quote.getTextLines().size());
        for (String lines : quote.getTextLines()) {
            builder.append(lines);
            builder.append('\n');
        }
        txtQuoteMovieTitle.setText(quote.getMovie());
        txtQuoteText.setText(builder.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
