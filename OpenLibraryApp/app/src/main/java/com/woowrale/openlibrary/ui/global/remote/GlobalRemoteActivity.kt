package com.woowrale.openlibrary.ui.global.remote

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListRemoteAdapterFilterable
import com.woowrale.openlibrary.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_global_remote.*
import kotlinx.android.synthetic.main.progress_view.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GlobalRemoteActivity : BaseActivity(), SeedListRemoteAdapterFilterable.BookListAdapterListener {

    @Inject
    lateinit var viewModel: GlobalRemoteViewModel

    private var seedList = ArrayList<Seed>()
    private val disposable = CompositeDisposable()
    private lateinit var mAdapter: SeedListRemoteAdapterFilterable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_remote)
        setSupportActionBar(findViewById(R.id.toolbar))

        mAdapter = SeedListRemoteAdapterFilterable(this, seedList, this)

        recyclerViewRemote.layoutManager = LinearLayoutManager(this)
        recyclerViewRemote.setHasFixedSize(true)
        recyclerViewRemote.itemAnimator = DefaultItemAnimator()
        recyclerViewRemote.adapter = mAdapter

        disposable.add(
            RxTextView.textChangeEvents(inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(viewModel.searchOlid(mAdapter).value!!)
        )

        viewModel.getSeedList(disposable, seedList, mAdapter, progressView)
    }

    override fun onBookDetails(seed: Seed) {
        TODO("Not yet implemented")
    }

    override fun onBookSaved(seed: Seed) {
        TODO("Not yet implemented")
    }

}