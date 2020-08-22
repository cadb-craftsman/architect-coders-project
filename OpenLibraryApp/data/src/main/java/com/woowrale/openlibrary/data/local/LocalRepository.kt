package com.woowrale.openlibrary.data.local

import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed


interface LocalOpenLibrarySource {

    fun searchBookByISBN(isbn: Int): List<Book>

    fun searhBookByOLID(olid: String): List<Book>

    fun searchSeedById(id: String): List<Seed>

    fun getAllSeeds(): List<Seed>

    fun saveSeed(seed: Seed)

    fun deleteSeed(seed: Seed)

    fun saveBook(book: Book)

    fun deleteBook(book: Book)

}

class LocalRepository(private val localOpenLibrarySource: LocalOpenLibrarySource) : LocalOpenLibrarySource {

    override fun searchBookByISBN(isbn: Int): List<Book> {
        return localOpenLibrarySource.searchBookByISBN(isbn)
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        return localOpenLibrarySource.searhBookByOLID(olid)
    }

    override fun searchSeedById(id: String): List<Seed> {
        return localOpenLibrarySource.searchSeedById(id)
    }

    override fun getAllSeeds(): List<Seed> {
        return localOpenLibrarySource.getAllSeeds()
    }

    override fun saveSeed(seed: Seed):Unit {
        return localOpenLibrarySource.saveSeed(seed)
    }

    override fun deleteSeed(seed: Seed) {
        localOpenLibrarySource.deleteSeed(seed)
    }

    override fun saveBook(book: Book) {
        localOpenLibrarySource.saveBook(book)
    }

    override fun deleteBook(book: Book) {
        localOpenLibrarySource.deleteBook(book)
    }
}