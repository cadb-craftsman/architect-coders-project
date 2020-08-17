package com.woowrale.openlibrary.ui.global.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.SeedListAdapterFilterable
import com.woowrale.openlibrary.usecase.observers.Observer
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class GlobalRemoteViewModel @Inject constructor() : ViewModel() {

    //private val TAG = GlobalRemoteViewModel::class.java.simpleName
    @Inject
    lateinit var useCaseFactory: UseCaseFactory
    private var seeds: MutableLiveData<List<Seed>> = MutableLiveData()

    fun getSeedList(
        seedList: ArrayList<Seed>,
        disposable: CompositeDisposable,
        mAdapter: SeedListAdapterFilterable
    ): LiveData<List<Seed>> {
        var seedObserver = SeedObserver(seedList, mAdapter)
        var params = GetSeedListUseCase.Params(BuildConfig.SEED_ID)
        disposable.add(useCaseFactory.getSeedListUseCase().execute(seedObserver, params))
        return seeds
    }

    class SeedObserver constructor(
        seeds: ArrayList<Seed>,
        adapter: SeedListAdapterFilterable
    ) : Observer<List<Seed>>() {

        private val mAdapter: SeedListAdapterFilterable = adapter
        private val seedList: ArrayList<Seed> = seeds

        override fun onSuccess(t: List<Seed>) {
            seedList.clear()
            seedList.addAll(t)
            mAdapter.notifyDataSetChanged()
        }

        override fun onError(e: Throwable) {}
    }
}

