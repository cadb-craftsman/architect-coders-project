package com.woowrale.openlibrary.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.ui.adapters.BookListAdapter
import com.woowrale.openlibrary.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_details.view.*
import javax.inject.Inject

class DetailsFragment : BaseFragment(), BookListAdapter.BookAdapterListener {

    @Inject
    lateinit var detailsViewModel: DetailsViewModel

    private lateinit var mAdapter: BookListAdapter
    private lateinit var bookList: List<Book>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        bookList = detailsViewModel.getBookList().value!!
        mAdapter = BookListAdapter(requireActivity().applicationContext, bookList, this)

        root.recyclerViewDetails.layoutManager = LinearLayoutManager(activity)
        root.recyclerViewDetails.setHasFixedSize(true)
        root.recyclerViewDetails.itemAnimator = DefaultItemAnimator()
        root.recyclerViewDetails.adapter = mAdapter

        return root
    }

    override fun onBookSelected(book: Book) {
        TODO("Not yet implemented")
    }
}