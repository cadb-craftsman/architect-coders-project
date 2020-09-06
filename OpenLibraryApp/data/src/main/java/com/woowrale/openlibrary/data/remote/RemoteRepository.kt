package com.woowrale.openlibrary.data.remote

import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed

interface RemoteOpenLibrarySource {

    fun searchBookByISBN(isbn: Int): List<Book>

    fun searhBookByOLID(olid: String): List<Book>

    fun searchSeedById(id: String): List<Seed>

}

class RemoteRepository(private val remoteOpenLibrarySource: RemoteOpenLibrarySource) : RemoteOpenLibrarySource {

    override fun searchBookByISBN(isbn: Int): List<Book> {
        return remoteOpenLibrarySource.searchBookByISBN(isbn)
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        return remoteOpenLibrarySource.searhBookByOLID(olid)
    }

    override fun searchSeedById(id: String): List<Seed> {
        return remoteOpenLibrarySource.searchSeedById(id)
    }
}