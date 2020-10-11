package com.woowrale.openlibrary.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.utils.inflate
import com.woowrale.openlibrary.utils.loadUrl
import java.util.*

class SeedListRemoteAdapterFilterable(
    private val seedList: List<Seed>,
    private val listener: BookListAdapterListener
) : RecyclerView.Adapter<SeedListRemoteAdapterFilterable.ViewHolder>(), Filterable {

    private var seedListFiltered: List<Seed>

    init {
        seedListFiltered = seedList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = parent.inflate(R.layout.item_book_remote)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return seedListFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val seed = seedListFiltered[position]
        holder.title.text = seed.title
        holder.olid.text = seed.olid

        if ((seed.picture != null) && !(seed.picture!!.url.equals(""))) {
            holder.thumbnail.loadUrl(seed.picture?.url!!)
        } else {
            holder.thumbnail.setImageResource(R.drawable.ic_open_library)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    seedListFiltered = seedList
                } else {
                    val filteredList = ArrayList<Seed>()
                    for (row in seedList) {
                        if (row.olid.toLowerCase().contains(charString.toLowerCase()) || row.olid.contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }

                    seedListFiltered = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = seedListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                seedListFiltered = filterResults.values as ArrayList<Seed>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView
        var olid: TextView
        var thumbnail: ImageView

        var btnSave: TextView
        var btnDetails: TextView

        init {
            title = view.findViewById(R.id.title)
            olid = view.findViewById(R.id.olid)
            thumbnail = view.findViewById(R.id.thumbnail)
            btnSave = view.findViewById(R.id.btnSave)
            btnDetails = view.findViewById(R.id.btnDetails)

            btnSave.setOnClickListener {
               listener.onBookSaved(seedList[adapterPosition])
            }

            btnDetails.setOnClickListener {
                listener.onBookDetails(seedList[adapterPosition])
            }
        }
    }

    interface BookListAdapterListener {
        fun onBookDetails(seed: Seed)
        fun onBookSaved(seed: Seed)
    }

}

