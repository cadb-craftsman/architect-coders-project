package com.woowrale.openlibrary.data

import com.woowrale.openlibrary.data.remote.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.mappers.toBook
import com.woowrale.openlibrary.data.remote.mappers.toSeed
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.utils.DataWrapper

class FakeRemoteDataSource : RemoteOpenLibrarySource {

    val seedList = DataWrapper.getSeedFromJson(ClassLoader.getSystemResource("seed-list.json").readText()).entries.map { it.toSeed() }
    val book = DataWrapper.getBookFromJson(ClassLoader.getSystemResource("book-olid.json").readText(), "OLID:OL23662890M").toBook()


    override fun searchBookByISBN(isbn: Int): List<Book> {
        val books = ArrayList<Book>()
        books.add(book)
        return books
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        val books = ArrayList<Book>()
        books.add(book)
        return books
    }

    override fun searchSeedById(id: String): List<Seed> {
       return seedList
    }
}