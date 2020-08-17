package com.woowrale.openlibrary.ui.global.remote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListAdapterFilterable
import com.woowrale.openlibrary.ui.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_global_remote.view.*
import javax.inject.Inject

class GlobalRemoteFragment: BaseFragment(), SeedListAdapterFilterable.BookListAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GlobalRemoteViewModel by viewModels {
        viewModelFactory
    }

    private val disposable = CompositeDisposable()
    private var seedList = ArrayList<Seed>()
    private lateinit var mAdapter: SeedListAdapterFilterable

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_global_remote, container, false)
        mAdapter = SeedListAdapterFilterable(requireActivity().applicationContext, seedList, this)

        root.recyclerViewRemote.layoutManager = LinearLayoutManager(activity)
        root.recyclerViewRemote.setHasFixedSize(true)
        root.recyclerViewRemote.itemAnimator = DefaultItemAnimator()
        root.recyclerViewRemote.adapter = mAdapter

        viewModel.getSeedList(seedList, disposable, mAdapter)

        return root
    }

    override fun onBookSelected(seed: Seed) {
        TODO("Not yet implemented")
    }

    override fun onBookDetails(seed: Seed) {
        TODO("Not yet implemented")
    }

    override fun onBookSaved(seed: Seed) {
        TODO("Not yet implemented")
    }

    override fun onBookDeleted(seed: Seed) {
        TODO("Not yet implemented")
    }
}