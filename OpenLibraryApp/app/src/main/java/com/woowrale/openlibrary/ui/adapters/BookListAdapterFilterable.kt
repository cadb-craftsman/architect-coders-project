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
import com.woowrale.openlibrary.domain.model.Book
import java.util.ArrayList

class BookListAdapterFilterable(
    private val context: Context,
    private val bookList: List<Book>,
    private val listener: BookListAdapterListener
) : RecyclerView.Adapter<BookListAdapterFilterable.ViewHolder>(), Filterable {

    private lateinit var bookListFiltered: List<Book>

    init {
        bookListFiltered = bookList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_global_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bookListFiltered!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookListFiltered!![position]
        holder.title.setText(book.thumbailUrl)
        holder.olid.setText(book.thumbailUrl)

        Glide.with(context)
            .load(book.thumbailUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.thumbnail)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    bookListFiltered = bookList
                } else {
                    val filteredList = ArrayList<Book>()
                    for (row in bookList) {
                        //if (row.name!!.toLowerCase().contains(charString.toLowerCase()) || row.phone!!.contains(charSequence)) {
                        //    filteredList.add(row)
                        //}
                    }

                    bookListFiltered = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = bookListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                bookListFiltered = filterResults.values as ArrayList<Book>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var title: TextView
        lateinit  var olid: TextView
        lateinit var thumbnail: ImageView

        init {
            //title = view.findViewById(R.id.title)
            //olid = view.findViewById(R.id.olid)
            //thumbnail = view.findViewById(R.id.thumbnail)

            view.setOnClickListener {
                listener.onBookSelected(bookListFiltered!![adapterPosition])
            }
        }
    }

    interface BookListAdapterListener {
        fun onBookSelected(book: Book)
    }

}

