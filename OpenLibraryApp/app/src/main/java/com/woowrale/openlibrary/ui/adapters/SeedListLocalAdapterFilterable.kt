package com.woowrale.openlibrary.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Seed
import java.util.*

class SeedListLocalAdapterFilterable(
    private val context: Context,
    private val seedList: List<Seed>,
    private val listener: BookListAdapterListener
) : RecyclerView.Adapter<SeedListLocalAdapterFilterable.ViewHolder>(), Filterable {

    private var seedListFiltered: List<Seed>

    init {
        seedListFiltered = seedList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_local, parent, false)

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
            Glide.with(context)
                .load(seed.picture?.url)
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.thumbnail)
        } else {
            holder.thumbnail.setImageResource(R.drawable.ic_open_library_logo)
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

        var btnDelete: TextView
        var btnDetails: TextView

        init {
            title = view.findViewById(R.id.title)
            olid = view.findViewById(R.id.olid)
            thumbnail = view.findViewById(R.id.thumbnail)
            btnDelete = view.findViewById(R.id.btnDelete)
            btnDetails = view.findViewById(R.id.btnDetails)

            btnDelete.setOnClickListener {
                listener.onBookDeleted(seedList[adapterPosition])
            }

            btnDetails.setOnClickListener {
                listener.onBookDetails(seedList[adapterPosition])
            }
        }
    }

    interface BookListAdapterListener {
        fun onBookDetails(seed: Seed)
        fun onBookDeleted(seed: Seed)
    }

}

