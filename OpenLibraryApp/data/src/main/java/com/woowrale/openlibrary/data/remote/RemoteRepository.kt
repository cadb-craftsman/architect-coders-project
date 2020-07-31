package com.woowrale.openlibrary.data.remote

import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Menu
import com.woowrale.openlibrary.domain.model.Seed

interface RemoteOpenLibrarySource {

    fun isEmpty(): Boolean

    fun getMenuList(): List<Menu>

    fun searchBookByISBN(isbn: Int): List<Book>

    fun searhBookByOLID(olid: String): List<Book>

    fun searchSeedById(id: String): List<Seed>

}

class RemoteRepository : RemoteOpenLibrarySource {

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMenuList(): List<Menu> {
        TODO("Not yet implemented")
    }

    override fun searchBookByISBN(isbn: Int): List<Book> {
        TODO("Not yet implemented")
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        TODO("Not yet implemented")
    }

    override fun searchSeedById(id: String): List<Seed> {
        TODO("Not yet implemented")
    }
}