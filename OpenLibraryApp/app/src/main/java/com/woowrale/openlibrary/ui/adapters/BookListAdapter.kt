package com.woowrale.openlibrary.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Book

class BookListAdapter(
    private val context: Context,
    private val bookList: List<Book>,
    private val listener: BookAdapterListener
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_detail_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        holder.title.text = book.details!!.title
        holder.subtitle.text = book.details!!.subTitle
        holder.language.text = book.details!!.languages
        holder.authors.text = book.details!!.authors

        if((book.thumbailUrl != null) && !(book.thumbailUrl.equals(""))){
            Glide.with(context)
                .load(book.thumbailUrl)
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.thumbnail)
        }else{
            holder.thumbnail.setImageResource(R.drawable.ic_open_library_logo)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var thumbnail: ImageView
        var title: TextView
        var subtitle: TextView
        var language: TextView
        var authors: TextView

        init {
            thumbnail = view.findViewById(R.id.thumbnail)
            title = view.findViewById(R.id.title)
            subtitle = view.findViewById(R.id.subtitle)
            language = view.findViewById(R.id.language)
            authors = view.findViewById(R.id.authors)

            view.setOnClickListener {
                listener.onBookSelected(bookList[adapterPosition])
            }
        }
    }

    interface BookAdapterListener {
        fun onBookSelected(book: Book)
    }

}

