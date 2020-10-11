package com.woowrale.openlibrary.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.utils.inflate
import com.woowrale.openlibrary.utils.loadUrl

class BookListAdapter(
    private val context: Context,
    private val bookList: List<Book>,
    private val listener: BookAdapterListener
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = parent.inflate(R.layout.item_book_detail)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        var title = context.getString(R.string.without_title)
        var subtitle = context.getString(R.string.without_subtitle)
        var language = context.getString(R.string.without_language)
        var authors = context.getString(R.string.without_authors)

        if(book.details?.title != null && book.details?.title?.length!! > 1){
            title = book.details?.title!!
        }

        if(book.details?.subTitle != null && book.details?.subTitle?.length!! > 1){
            subtitle = book.details?.subTitle!!
        }

        if(book.details?.languages != null && book.details?.languages?.length !! > 1){
            language = book.details?.languages!!
        }

        if(book.details?.authors != null && book.details?.authors?.length!! > 1){
            authors = book.details?.authors!!
        }

        holder.title.text = title
        holder.subtitle.text = subtitle
        holder.language.text = language
        holder.authors.text = authors

        if((book.thumbailUrl != null) && !(book.thumbailUrl.equals(""))){
            holder.thumbnail.loadUrl(book.thumbailUrl!!)
        }else{
            holder.thumbnail.setImageResource(R.drawable.ic_open_library)
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

