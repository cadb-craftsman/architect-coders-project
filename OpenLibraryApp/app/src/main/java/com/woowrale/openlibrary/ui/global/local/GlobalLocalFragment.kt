package com.woowrale.openlibrary.ui.global.local

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListAdapterFilterable
import com.woowrale.openlibrary.ui.adapters.SeedListAdapterFilterable.BookListAdapterListener
import com.woowrale.openlibrary.ui.base.BaseFragment
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_global_local.view.*
import javax.inject.Inject

class GlobalLocalFragment : BaseFragment(), BookListAdapterListener {

    @Inject
    lateinit var globalLocalViewModel: GlobalLocalViewModel
    private lateinit var mAdapter: SeedListAdapterFilterable
    private lateinit var seedList: List<Seed>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        globalLocalViewModel = ViewModelProviders.of(this).get(GlobalLocalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_global_local, container, false)
        root.localProgressBar.visibility = View.VISIBLE

        seedList = globalLocalViewModel.getSeedList().value!!
        mAdapter = SeedListAdapterFilterable(requireActivity().applicationContext, seedList, this)

        root.recyclerViewLocal.layoutManager = LinearLayoutManager(activity)
        root.recyclerViewLocal.setHasFixedSize(true)
        root.recyclerViewLocal.itemAnimator = DefaultItemAnimator()
        root.recyclerViewLocal.adapter = mAdapter
        root.localProgressBar.visibility = View.GONE

        return root
    }

    override fun onBookSelected(seed: Seed) {
        Log.e("SEED", "seed " + seed.url)
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