package com.woowrale.openlibrary.data.local.datasource

import com.woowrale.openlibrary.data.local.LocalOpenLibrarySource
import com.woowrale.openlibrary.data.local.database.OpenLibraryDao
import com.woowrale.openlibrary.data.local.database.OpenLibraryDatabase
import com.woowrale.openlibrary.data.local.mappers.toBook
import com.woowrale.openlibrary.data.local.mappers.toBookEntity
import com.woowrale.openlibrary.data.local.mappers.toSeed
import com.woowrale.openlibrary.data.local.mappers.toSeedEntity
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed

class GetLocalOpenLibrarySource(openApiDatabase: OpenLibraryDatabase) : LocalOpenLibrarySource {

    private var openLibraryDao: OpenLibraryDao = openApiDatabase.openLibraryDao()

    override fun searchBookByISBN(isbn: Int): List<Book> {
        TODO("Not yet implemented")
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        val bookList = openLibraryDao.findBookById(olid).map { it.toBook() }
        if (bookList.isNullOrEmpty()) {
            return emptyList()
        }
        return bookList
    }

    override fun searchSeedById(id: String): List<Seed> {
        return openLibraryDao.findSeedById(id).map { it.toSeed() }
    }

    override fun getAllSeeds(): List<Seed> {
        return openLibraryDao.getAllSeeds().map { it.toSeed() }
    }

    override fun saveSeed(seed: Seed): Boolean {
        val i = openLibraryDao.insertSeed(seed.toSeedEntity())
        if (i > 0) {
            return true
        }
        return false
    }

    override fun deleteSeed(seed: Seed): Boolean {
        val d = openLibraryDao.deleteSeed(seed.olid)
        if (d >= 1) {
            return true
        }
        return false
    }

    override fun saveBook(book: Book): Boolean {
        val i = openLibraryDao.insertBook(book.toBookEntity())
        if (i > 0) {
            return true
        }
        return false
    }

    override fun deleteBook(book: Book): Boolean {
        val d = openLibraryDao.deleteBook(book.bibKey!!)
        if(d >= 1){
            return true
        }
        return false
    }

}