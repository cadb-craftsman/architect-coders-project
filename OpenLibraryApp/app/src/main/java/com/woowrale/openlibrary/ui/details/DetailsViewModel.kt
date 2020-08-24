package com.woowrale.openlibrary.ui.details

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.adapters.BookListAdapter
import com.woowrale.openlibrary.ui.adapters.SeedListRemoteAdapterFilterable
import com.woowrale.openlibrary.ui.global.local.GlobalLocalViewModel
import com.woowrale.openlibrary.ui.global.remote.SeedsObserver
import com.woowrale.openlibrary.usecase.observers.Observer
import com.woowrale.openlibrary.usecase.usecases.GetDetailBookUseCase
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import com.woowrale.openlibrary.utils.DataWrapper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel  @Inject constructor(): ViewModel() {

    private val TAG = DetailsViewModel::class.java.simpleName

    @Inject
    lateinit var useCaseFactory: UseCaseFactory

    private var books: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(disposable: CompositeDisposable,
                 bookList: List<Book>,
                 olid: String,
                 mAdapter: BookListAdapter,
                 progressBar: FrameLayout
    ): LiveData<List<Book>>{
        val bookObserver = BooksObserver(bookList as ArrayList<Book>, mAdapter, progressBar)
        val params = GetDetailBookUseCase.Params(olid, BuildConfig.ENV_REMOTE)
        disposable.add(useCaseFactory.getDetailBookUseCase().execute(bookObserver, params))
        return books
    }
}

class BooksObserver constructor(
    books: ArrayList<Book>,
    adapter: BookListAdapter,
    progressBar: FrameLayout
) : Observer<List<Book>>() {

    private val TAG = BooksObserver::class.java.simpleName
    private val booksList: ArrayList<Book> = books
    private val mAdapter: BookListAdapter = adapter
    private val progressBar: FrameLayout = progressBar

    override fun onSuccess(t: List<Book>) {
        booksList.clear()
        booksList.addAll(t)
        mAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}