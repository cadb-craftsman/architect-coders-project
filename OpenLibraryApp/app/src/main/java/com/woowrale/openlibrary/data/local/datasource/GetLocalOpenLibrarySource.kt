package com.woowrale.openlibrary.data.local.datasource

import com.woowrale.openlibrary.data.local.LocalOpenLibrarySource
import com.woowrale.openlibrary.data.local.database.OpenLibraryDao
import com.woowrale.openlibrary.data.local.database.OpenLibraryDatabase
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Menu
import com.woowrale.openlibrary.domain.model.Seed

class GetLocalOpenLibrarySource(openApiDatabase: OpenLibraryDatabase) : LocalOpenLibrarySource {

    private var openLibraryDao: OpenLibraryDao = openApiDatabase.openLibraryDao()

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