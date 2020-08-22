package com.woowrale.openlibrary.ui.global.remote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListRemoteAdapterFilterable
import com.woowrale.openlibrary.ui.base.BaseFragment
import com.woowrale.openlibrary.ui.dialogs.MessageDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_global_remote.view.*
import kotlinx.android.synthetic.main.progress_view.view.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GlobalRemoteFragment: BaseFragment(), SeedListRemoteAdapterFilterable.BookListAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GlobalRemoteViewModel by viewModels {
        viewModelFactory
    }

    private var seedList = ArrayList<Seed>()
    private val disposable = CompositeDisposable()
    private lateinit var mAdapter: SeedListRemoteAdapterFilterable
    private lateinit var messageDialog: MessageDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_global_remote, container, false)
        mAdapter = SeedListRemoteAdapterFilterable(requireActivity().applicationContext, seedList, this)

        root.recyclerViewRemote.layoutManager = LinearLayoutManager(activity)
        root.recyclerViewRemote.setHasFixedSize(true)
        root.recyclerViewRemote.itemAnimator = DefaultItemAnimator()
        root.recyclerViewRemote.adapter = mAdapter

        disposable.add(
            RxTextView.textChangeEvents(root.inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(viewModel.searchOlid(mAdapter).value!!)
        )

        viewModel.getSeedList(disposable, seedList, mAdapter, root.progressView)
        messageDialog = MessageDialog()
        return root
    }

    override fun onBookDetails(seed: Seed) {
        TODO("Not yet implemented")
    }

    override fun onBookSaved(seed: Seed) {
        viewModel.saveSeed(disposable, seed, messageDialog)
    }

}