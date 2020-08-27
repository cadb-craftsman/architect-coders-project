package com.woowrale.openlibrary.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.usecase.observers.Observer
import com.woowrale.openlibrary.usecase.usecases.GetDetailBookUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {

    private val TAG = DetailsViewModel::class.java.simpleName

    @Inject
    lateinit var useCaseFactory: UseCaseFactory
    private var books: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(disposable: CompositeDisposable, olid: String, env: String): LiveData<List<Book>> {
        disposable.add(
            useCaseFactory.getDetailBookUseCase().execute(
                BooksObserver(books),
                GetDetailBookUseCase.Params(olid, env)
            )
        )
        return books
    }
}

class BooksObserver constructor(
    private val books: MutableLiveData<List<Book>>
) : Observer<List<Book>>() {

    private val TAG = BooksObserver::class.java.simpleName

    override fun onSuccess(t: List<Book>) {
        books.value = t

    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}