package com.woowrale.openlibrary.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.ui.adapters.BookListAdapter
import com.woowrale.openlibrary.ui.base.BaseFragment
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
        val env = bundle?.getString("env")
        observeGetBooks(root, disposable, olid!!, env!!)
        return root
    }

    override fun onBookSelected(book: Book) {

    }

    private fun observeGetBooks(view: View, disposable: CompositeDisposable, olid: String, env: String){
        viewModel.getBooks(disposable, olid, env).observe(viewLifecycleOwner, Observer {
            if(it != null){
                mAdapter = BookListAdapter(requireActivity().applicationContext, it, this)
                createRecyclerView(view, mAdapter)
                setProgressViewVisibility(view, false)
            }
        })
    }

    private fun createRecyclerView(view: View, mAdapter: BookListAdapter) {
        view.recyclerViewDetails.layoutManager = LinearLayoutManager(activity)
        view.recyclerViewDetails.setHasFixedSize(true)
        view.recyclerViewDetails.itemAnimator = DefaultItemAnimator()
        view.recyclerViewDetails.adapter = mAdapter
    }

    private fun setProgressViewVisibility(view: View, isVisible: Boolean = true) {
        if (isVisible) {
            view.progressView.visibility = View.VISIBLE
        } else {
            view.progressView.visibility = View.GONE
        }
    }
}