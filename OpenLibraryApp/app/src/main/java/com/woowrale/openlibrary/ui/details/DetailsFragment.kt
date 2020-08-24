package com.woowrale.openlibrary.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.ui.adapters.BookListAdapter
import com.woowrale.openlibrary.ui.base.BaseFragment
import com.woowrale.openlibrary.ui.global.local.GlobalLocalViewModel
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.progress_view.view.*
import javax.inject.Inject

class DetailsFragment : BaseFragment(), BookListAdapter.BookAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DetailsViewModel by viewModels {
        viewModelFactory
    }

    private var bookList = ArrayList<Book>()
    private val disposable = CompositeDisposable()
    private lateinit var mAdapter: BookListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        val bundle = arguments
        val olid = bundle?.getString("olid")
        mAdapter = BookListAdapter(requireActivity().applicationContext, bookList, this)

        root.recyclerViewDetails.layoutManager = LinearLayoutManager(activity)
        root.recyclerViewDetails.setHasFixedSize(true)
        root.recyclerViewDetails.itemAnimator = DefaultItemAnimator()
        root.recyclerViewDetails.adapter = mAdapter

        viewModel.getBooks(disposable, bookList, olid!!, mAdapter, root.progressView)

        return root
    }

    override fun onBookSelected(book: Book) {

    }
}