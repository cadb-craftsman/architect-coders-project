package com.woowrale.openlibrary.ui.global.remote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListRemoteAdapterFilterable
import com.woowrale.openlibrary.ui.base.BaseFragment
import com.woowrale.openlibrary.ui.dialogs.AlertMessageDialog
import com.woowrale.openlibrary.ui.dialogs.ConfirmMessageDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_global_remote.view.*
import kotlinx.android.synthetic.main.progress_view.view.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GlobalRemoteFragment : BaseFragment(),
    SeedListRemoteAdapterFilterable.BookListAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GlobalRemoteViewModel by viewModels {
        viewModelFactory
    }

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_global_remote, container, false)
        observeGetSeeds(root, disposable)

        return root
    }

    override fun onBookDetails(seed: Seed) {
        val bundle = Bundle()
        bundle.putString("olid", seed.olid)
        bundle.putString("env", BuildConfig.ENV_REMOTE)
        this.findNavController().navigate(R.id.nav_details, bundle)
    }

    override fun onBookSaved(seed: Seed) {
        viewModel.saveSeed(disposable, seed).observe(viewLifecycleOwner, Observer {
            showMessageDialog()
        })
    }

    private fun observeGetSeeds(view: View, disposable: CompositeDisposable) {
        viewModel.getSeedList(disposable, BuildConfig.SEED_ID, BuildConfig.ENV_REMOTE)
            .observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    val mAdapter =
                        SeedListRemoteAdapterFilterable(
                            requireActivity().applicationContext,
                            it,
                            this
                        )
                    createRecyclerView(view, mAdapter)
                    createFilterableSearch(view, disposable, mAdapter)
                    setProgressViewVisibility(view, false)
                }
            })
    }

    private fun createFilterableSearch(
        view: View,
        disposable: CompositeDisposable,
        mAdapter: SeedListRemoteAdapterFilterable
    ) {
        disposable.add(
            RxTextView.textChangeEvents(view.inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(viewModel.searchOlid(mAdapter).value!!)
        )
    }

    private fun createRecyclerView(view: View, mAdapter: SeedListRemoteAdapterFilterable) {
        view.recyclerViewRemote.layoutManager = LinearLayoutManager(activity)
        view.recyclerViewRemote.setHasFixedSize(true)
        view.recyclerViewRemote.itemAnimator = DefaultItemAnimator()
        view.recyclerViewRemote.adapter = mAdapter
    }

    private fun setProgressViewVisibility(view: View, isVisible: Boolean = true) {
        if (isVisible) {
            view.progressView.visibility = View.VISIBLE
        } else {
            view.progressView.visibility = View.GONE
        }
    }

    private fun showMessageDialog() {
        ConfirmMessageDialog.newInstance().show(requireActivity().supportFragmentManager, "Alert Message Dialog")
    }
}