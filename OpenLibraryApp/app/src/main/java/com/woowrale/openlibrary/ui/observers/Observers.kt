package com.woowrale.openlibrary.ui.observers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.usecase.observers.Observer

class SaveSeedObserver constructor(private val isSaved: MutableLiveData<Boolean>) : Observer<Boolean>() {

    private val TAG = SaveSeedObserver::class.java.simpleName

    override fun onSuccess(t: Boolean) {
        super.onSuccess(t)
        isSaved.value = t
        Log.e(TAG, "Se ha procesado correctamente")
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}

class GetSeedsObserver constructor(private val seeds: MutableLiveData<List<Seed>>) : Observer<List<Seed>>() {

    private val TAG = GetSeedsObserver::class.java.simpleName

    override fun onSuccess(t: List<Seed>) {
        seeds.value = t
        Log.e(TAG, "Se ha procesado correctamente")
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}

class DeleteSeedObserver constructor(private val isSaved: MutableLiveData<Boolean>) : Observer<Boolean>() {

    private val TAG = DeleteSeedObserver::class.java.simpleName

    override fun onSuccess(t: Boolean) {
        super.onSuccess(t)
        isSaved.value = t
        Log.e(TAG, "Se ha procesado correctamente")
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}

class BooksObserver constructor(private val books: MutableLiveData<List<Book>>) : Observer<List<Book>>() {

    private val TAG = BooksObserver::class.java.simpleName

    override fun onSuccess(t: List<Book>) {
        books.value = t

    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "Se ha producido una excepcion" + e.message)
    }
}