package com.woowrale.openlibrary.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.ui.observers.BooksObserver
import com.woowrale.openlibrary.ui.observers.SaveBookObserver
import com.woowrale.openlibrary.usecase.usecases.GetDetailBookUseCase
import com.woowrale.openlibrary.usecase.usecases.SaveBookUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {

    private val TAG = DetailsViewModel::class.java.simpleName

    @Inject
    lateinit var useCaseFactory: UseCaseFactory
    private var books: MutableLiveData<List<Book>> = MutableLiveData()
    private var isSaved: MutableLiveData<Boolean> = MutableLiveData()

    fun getBooks(disposable: CompositeDisposable, olid: String, env: String): LiveData<List<Book>> {
        disposable.add(
            useCaseFactory.getDetailBookUseCase().execute(
                BooksObserver(books),
                GetDetailBookUseCase.Params(olid, env)
            )
        )
        return books
    }

    fun saveBook(disposable: CompositeDisposable, book: Book): LiveData<Boolean> {
        disposable.add(
            useCaseFactory.saveBookUseCase().execute(
                SaveBookObserver(isSaved),
                SaveBookUseCase.Params(book)
            )
        )
        return isSaved
    }
}

