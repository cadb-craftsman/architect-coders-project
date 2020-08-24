package com.woowrale.openlibrary.ui.global.remote

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListRemoteAdapterFilterable
import com.woowrale.openlibrary.ui.dialogs.MessageDialog
import com.woowrale.openlibrary.usecase.observers.Observer
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import com.woowrale.openlibrary.usecase.usecases.SaveSeedUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GlobalRemoteViewModel @Inject constructor() : ViewModel() {

    private val TAG = GlobalRemoteViewModel::class.java.simpleName

    @Inject
    lateinit var useCaseFactory: UseCaseFactory
    private var seeds: MutableLiveData<List<Seed>> = MutableLiveData()
    private var unit: MutableLiveData<Unit> = MutableLiveData()
    private val textSearch: MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> =
        MutableLiveData()

    fun searchOlid(mAdapter: SeedListRemoteAdapterFilterable): LiveData<DisposableObserver<TextViewTextChangeEvent>> {
        textSearch.setValue(object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d(
                    TAG,
                    "Search query: " + textViewTextChangeEvent.text()
                )
                mAdapter.getFilter().filter(textViewTextChangeEvent.text())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {}
        })
        return textSearch
    }

    fun getSeedList(
        disposable: CompositeDisposable,
        seedList: List<Seed>,
        mAdapter: SeedListRemoteAdapterFilterable,
        progressBar: FrameLayout
    ): LiveData<List<Seed>> {
        val seedObserver = SeedsObserver(seedList as ArrayList<Seed>, mAdapter, progressBar)
        val params = GetSeedListUseCase.Params(BuildConfig.SEED_ID, BuildConfig.ENV_REMOTE)
        disposable.add(useCaseFactory.getSeedListUseCase().execute(seedObserver, params))
        return seeds
    }

    fun saveSeed(
        disposable: CompositeDisposable,
        seed: Seed,
        messageDialog: MessageDialog
    ): LiveData<Unit> {
        val seedObserver = SeedObserver(seed, messageDialog)
        val params = SaveSeedUseCase.Params(seed)
        disposable.add(useCaseFactory.saveSeedUseCase().execute(seedObserver, params))
        return unit
    }
}

class SeedsObserver constructor(
    seedsList: ArrayList<Seed>,
    adapter: SeedListRemoteAdapterFilterable,
    progressBar: FrameLayout
) : Observer<List<Seed>>() {

    private val TAG = SeedsObserver::class.java.simpleName
    private val seedList: ArrayList<Seed> = seedsList
    private val mAdapter: SeedListRemoteAdapterFilterable = adapter
    private val progressBar: FrameLayout = progressBar

    override fun onSuccess(t: List<Seed>) {
        seedList.clear()
        seedList.addAll(t)
        mAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}

class SeedObserver constructor(
    seed: Seed, messageDialog: MessageDialog
) : Observer<Unit>() {

    private val TAG = SeedObserver::class.java.simpleName

    private val seed = seed
    private val messageDialog = messageDialog

    override fun onSuccess(t: Unit) {
        super.onSuccess(t)
        //messageDialog.showMessage("Save Boook", "The book was saved")
        Log.e(TAG, "El proceso se ha ejecutado correctamente")
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}
