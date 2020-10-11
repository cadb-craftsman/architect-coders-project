package com.woowrale.openlibrary.ui.global.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListRemoteAdapterFilterable
import com.woowrale.openlibrary.ui.observers.GetSeedsObserver
import com.woowrale.openlibrary.ui.observers.SaveSeedObserver
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import com.woowrale.openlibrary.usecase.usecases.SaveSeedUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GlobalRemoteViewModel @Inject constructor() : ViewModel() {

    private val TAG = GlobalRemoteViewModel::class.java.simpleName

    @Inject
    lateinit var useCaseFactory: UseCaseFactory
    val seeds = MutableLiveData<List<Seed>>()
    val isSaved = MutableLiveData<Boolean>()
    val textSearch: MutableLiveData<DisposableObserver<TextViewTextChangeEvent>> = MutableLiveData()

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
        seedId: String,
        env: String
    ): LiveData<List<Seed>> {
        disposable.add(
            useCaseFactory.getSeedListUseCase()
                .execute(GetSeedsObserver(seeds), GetSeedListUseCase.Params(seedId, env))
        )
        return seeds
    }

    fun saveSeed(disposable: CompositeDisposable, seed: Seed): LiveData<Boolean> {
        isSaved.value = false
        disposable.add(
            useCaseFactory.saveSeedUseCase()
                .execute(SaveSeedObserver(isSaved), SaveSeedUseCase.Params(seed))
        )
        return isSaved
    }
}