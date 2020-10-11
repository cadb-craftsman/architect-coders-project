package com.woowrale.openlibrary.ui.global.local

import android.app.AlertDialog
import android.content.DialogInterface
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_global_local.view.*
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
        observeGetSeeds(root, disposable)
        return root
    }

    override fun onBookDetails(seed: Seed) {
        val bundle = Bundle()
        bundle.putString("olid", seed.olid)
        bundle.putString("env", BuildConfig.ENV_LOCAL)
        this.findNavController().navigate(R.id.nav_details, bundle)
    }

    override fun onBookDeleted(seed: Seed) {
        showQuestionDialog(seed)
    }

    private fun observeGetSeeds(view: View, disposable: CompositeDisposable) {
        viewModel.getSeedList(disposable, BuildConfig.SEED_ID, BuildConfig.ENV_LOCAL)
            .observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    mAdapter = SeedListLocalAdapterFilterable(it,this)
                    createRecyclerView(view, mAdapter)
                    createFilterableSearch(view, disposable, mAdapter)
                    setProgressViewVisibility(view, false)
                }
            })
    }

    private fun updateGetSeeds(disposable: CompositeDisposable) {
        viewModel.seeds.removeObservers(this)
        viewModel.getSeedList(disposable, BuildConfig.SEED_ID, BuildConfig.ENV_LOCAL)
            .observe(this, Observer {
                mAdapter.notifyDataSetChanged()
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

    private fun showQuestionDialog(seed: Seed) {
        var builder = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)

        builder.setTitle(getString(R.string.title_question_message))
        builder.setMessage(getString(R.string.body_question_message))
        builder.setPositiveButton(
            getString(R.string.button_ok),
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                observerDeleteSeed(seed)
            })

        builder.setNegativeButton(
            getString(R.string.button_cancel),
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })

        builder.create().show()
    }

    private fun observerDeleteSeed(seed: Seed){
        viewModel.isSaved.removeObservers(this)
        viewModel.deleteSeed(disposable, seed)
            .observe(this, Observer {
                if (it) {
                    showMessageDialog()
                    updateGetSeeds(disposable)
                }
            })
    }
}

