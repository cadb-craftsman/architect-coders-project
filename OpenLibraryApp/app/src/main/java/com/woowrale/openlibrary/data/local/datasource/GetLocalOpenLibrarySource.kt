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
        return openLibraryDao.findBookById(olid).map { it.toBook() }
    }

    override fun searchSeedById(id: String): List<Seed> {
        return openLibraryDao.findSeedById(id).map { it.toSeed() }
    }

    override fun getAllSeeds(): List<Seed> {
        return openLibraryDao.getAllSeeds().map { it.toSeed() }
    }

    override fun saveSeed(seed: Seed) {
        openLibraryDao.insertSeed(seed.toSeedEntity())
    }

    override fun deleteSeed(seed: Seed) {
        openLibraryDao.deleteSeed(seed.url)
    }

    override fun saveBook(book: Book) {
        openLibraryDao.insertBook(book.toBookEntity())
    }

    override fun deleteBook(book: Book) {
        openLibraryDao.deleteBook(book.bibKey)
    }

}