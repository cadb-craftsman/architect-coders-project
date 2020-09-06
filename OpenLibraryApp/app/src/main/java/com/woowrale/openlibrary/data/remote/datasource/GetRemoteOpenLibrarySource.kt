package com.woowrale.openlibrary.data.remote.datasource

import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.data.remote.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.mappers.toBook
import com.woowrale.openlibrary.data.remote.mappers.toSeed
import com.woowrale.openlibrary.data.remote.model.response.BookResponse
import com.woowrale.openlibrary.data.remote.server.ApiService
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.utils.DataUtils
import java.io.IOException

class GetRemoteOpenLibrarySource(apiService: ApiService) :  RemoteOpenLibrarySource {

    private val apiService: ApiService = apiService

    override fun searchBookByISBN(isbn: Int): List<Book> {
        TODO("Not yet implemented")
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        var bookList = ArrayList<Book>()

        val fullUrl = DataUtils.getBookUrl(olid)
        val mapBook: Map<String, BookResponse> =  apiService.getBookList(fullUrl).execute().body()!!
        val olidId = BuildConfig.BOOK_OLID + olid
        val book = mapBook.get(olidId)
        if(book != null){
            bookList.add(book!!.toBook())
        }
        return bookList
    }

    override fun searchSeedById(id: String): List<Seed> {
        return try {
            val fullUrl = DataUtils.getSeedsUrl(id)
            apiService.getSeedList(fullUrl).execute().body()!!.entries.map { it.toSeed() }
        } catch (e: IOException) {
            return listOfNotNull()
        }

    }


}

