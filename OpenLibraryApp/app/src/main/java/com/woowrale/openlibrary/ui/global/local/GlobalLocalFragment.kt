package com.woowrale.openlibrary.ui.global.local

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
import com.woowrale.openlibrary.ui.adapters.SeedListLocalAdapterFilterable
import com.woowrale.openlibrary.ui.base.BaseFragment
import com.woowrale.openlibrary.ui.dialogs.AlertMessageDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_global_local.view.*
import kotlinx.android.synthetic.main.fragment_global_local.view.inputSearch
import kotlinx.android.synthetic.main.fragment_global_remote.view.*
import kotlinx.android.synthetic.main.progress_view.view.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GlobalLocalFragment : BaseFragment(), SeedListLocalAdapterFilterable.BookListAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GlobalLocalViewModel by viewModels {
        viewModelFactory
    }

    private val disposable = CompositeDisposable()
    private lateinit var mAdapter: SeedListLocalAdapterFilterable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_global_local, container, false)
        observeGetSeeds(disposable, root)
        return root
    }

    override fun onBookDetails(seed: Seed) {
        val bundle = Bundle()
        bundle.putString("olid", seed.olid)
        this.findNavController().navigate(R.id.nav_details, bundle)
    }

    override fun onBookDeleted(seed: Seed) {
        viewModel.deleteSeed(disposable, seed)
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                showAlertMessageDialog()

            })
    }

    private fun observeGetSeeds(disposable: CompositeDisposable, view: View) {
        viewModel.getSeedList(disposable, BuildConfig.SEED_ID, BuildConfig.ENV_LOCAL)
            .observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    val mAdapter = SeedListLocalAdapterFilterable(
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
        mAdapter: SeedListLocalAdapterFilterable
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

    private fun createRecyclerView(view: View, mAdapter: SeedListLocalAdapterFilterable) {
        view.recyclerViewLocal.layoutManager = LinearLayoutManager(activity)
        view.recyclerViewLocal.setHasFixedSize(true)
        view.recyclerViewLocal.itemAnimator = DefaultItemAnimator()
        view.recyclerViewLocal.adapter = mAdapter
    }

    private fun setProgressViewVisibility(view: View, isVisible: Boolean = true) {
        if (isVisible) {
            view.progressView.visibility = View.VISIBLE
        } else {
            view.progressView.visibility = View.GONE
        }
    }

    private fun showAlertMessageDialog() {
        AlertMessageDialog.newInstance()
            .show(requireActivity().supportFragmentManager, "Alert Message Dialog")
    }
}

