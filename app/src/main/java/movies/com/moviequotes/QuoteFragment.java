package movies.com.moviequotes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import movies.com.moviequotes.services.Line;

public class QuoteFragment extends Fragment {

    private static final String SAVE_INSTANCE_QUOTE_TEXT_KEY = "quoteText";
    private static final String SAVE_INSTANCE_MOVIE_TEXT_KEY = "movieText";
    @Bind(R.id.txtQuoteMovieTitle)
    TextView txtQuoteMovieTitle;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    private List<Line> quoteLines;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState!=null && !savedInstanceState.isEmpty()) {
            quoteLines = savedInstanceState.getParcelableArrayList(SAVE_INSTANCE_QUOTE_TEXT_KEY);
            updateRecyclerView(quoteLines);
            txtQuoteMovieTitle.setText((CharSequence) savedInstanceState.get(SAVE_INSTANCE_MOVIE_TEXT_KEY));
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (quoteLines!=null) {
            outState.putParcelableArrayList(SAVE_INSTANCE_QUOTE_TEXT_KEY, (ArrayList<Line>) quoteLines);
        }
        outState.putString(SAVE_INSTANCE_MOVIE_TEXT_KEY, txtQuoteMovieTitle.getText().toString());
    }

    public void showQuote(MovieQuote quote) {
        txtQuoteMovieTitle.setText(quote.getTitle());
        quoteLines = quote.getQuote().getLines();
        updateRecyclerView(quoteLines);
    }

    private void updateRecyclerView(List<Line> lines) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Adapter(lines));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
