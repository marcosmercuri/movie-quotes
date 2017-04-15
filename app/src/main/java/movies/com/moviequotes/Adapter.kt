package movies.com.moviequotes

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.quote.view.*
import movies.com.moviequotes.services.Line


class Adapter(val items:List<Line>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.quote))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.itemView) {
            txtCharacter.text = item.character + if (item.character=="") "" else ": "
            txtLine.text = item.text
        }
    }

    override fun getItemCount(): Int = items.size
}
