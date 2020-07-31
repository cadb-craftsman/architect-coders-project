package com.woowrale.openlibrary.data.local

import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Menu
import com.woowrale.openlibrary.domain.model.Seed


interface LocalOpenLibrarySource {

    fun isEmpty(): Boolean

    fun getMenuList(): List<Menu>

    fun searchBookByISBN(isbn: Int): List<Book>

    fun searhBookByOLID(olid: String): List<Book>

    fun searchSeedById(id: String): List<Seed>

    fun saveBook(book: Book)

    fun deleteBook(book: Book)

}

class LocalRepository : LocalOpenLibrarySource {

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

    override fun saveBook(book: Book) {
        TODO("Not yet implemented")
    }

    override fun deleteBook(book: Book) {
        TODO("Not yet implemented")
    }
}