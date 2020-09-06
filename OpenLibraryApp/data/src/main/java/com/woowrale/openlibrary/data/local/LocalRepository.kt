package com.woowrale.openlibrary.data.local

import com.sun.org.apache.xpath.internal.operations.Bool
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed


interface LocalOpenLibrarySource {

    fun searchBookByISBN(isbn: Int): List<Book>

    fun searhBookByOLID(olid: String): List<Book>

    fun searchSeedById(id: String): List<Seed>

    fun getAllSeeds(): List<Seed>

    fun saveSeed(seed: Seed): Boolean

    fun deleteSeed(seed: Seed): Boolean

    fun saveBook(book: Book): Boolean

    fun deleteBook(book: Book): Boolean

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

    override fun saveSeed(seed: Seed): Boolean{
        return localOpenLibrarySource.saveSeed(seed)
    }

    override fun deleteSeed(seed: Seed):Boolean {
        return localOpenLibrarySource.deleteSeed(seed)
    }

    override fun saveBook(book: Book) : Boolean{
        return localOpenLibrarySource.saveBook(book)
    }

    override fun deleteBook(book: Book):Boolean {
        return localOpenLibrarySource.deleteBook(book)
    }
}