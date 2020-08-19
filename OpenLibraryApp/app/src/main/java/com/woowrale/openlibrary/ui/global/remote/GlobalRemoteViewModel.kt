package com.woowrale.openlibrary.ui.global.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListAdapterFilterable
import com.woowrale.openlibrary.usecase.observers.Observer
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GlobalRemoteViewModel @Inject constructor() : ViewModel() {

    private val TAG = GlobalRemoteViewModel::class.java.simpleName

    @Inject
    lateinit var useCaseFactory: UseCaseFactory
    private var seeds: MutableLiveData<List<Seed>> = MutableLiveData()
    private val textSearch: MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> = MutableLiveData()

    fun searchOlid(mAdapter: SeedListAdapterFilterable): LiveData<DisposableObserver<TextViewTextChangeEvent>> {
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
        mAdapter: SeedListAdapterFilterable
    ): LiveData<List<Seed>> {
        var seedObserver = SeedObserver(seedList as ArrayList<Seed>, mAdapter)
        var params = GetSeedListUseCase.Params(BuildConfig.SEED_ID)
        disposable.add(useCaseFactory.getSeedListUseCase().execute(seedObserver, params))
        return seeds
    }

    class SeedObserver constructor(
        seedsList: ArrayList<Seed>,
        adapter: SeedListAdapterFilterable
    ) : Observer<List<Seed>>() {

        private val TAG = SeedObserver::class.java.simpleName
        private val seedList: ArrayList<Seed> = seedsList
        private val mAdapter: SeedListAdapterFilterable = adapter

        override fun onSuccess(t: List<Seed>) {
            seedList.clear()
            seedList.addAll(t)
            mAdapter.notifyDataSetChanged()
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "Se ha producido una excepcion" + e.message)
        }
    }
}

