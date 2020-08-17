package com.woowrale.openlibrary.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.utils.DataWrapper
import javax.inject.Inject

class DetailsViewModel  @Inject constructor(): ViewModel() {

    private var bookList: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBookList(): LiveData<List<Book>>{
        val bList = ArrayList<Book>()
        bList.add(DataWrapper.book)
        bookList.value = bList
        return bookList
    }
}