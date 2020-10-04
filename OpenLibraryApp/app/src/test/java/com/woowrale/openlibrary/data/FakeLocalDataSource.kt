package com.woowrale.openlibrary.data

import com.woowrale.openlibrary.data.local.LocalOpenLibrarySource
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed

class FakeLocalDataSource : LocalOpenLibrarySource {

    var seedList = ArrayList<Seed>()
    var bookList = ArrayList<Book>()

    override fun searchBookByISBN(isbn: Int): List<Book> {
        var books = ArrayList<Book>()

        bookList.forEach {
            if(it.bibKey?.toInt() == isbn){
                books.add(it)
            }
        }
        return books
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        var books = ArrayList<Book>()

        bookList.forEach {
            if(it.bibKey == olid) {
                books.add(it)
            }
        }
        return books
    }

    override fun searchSeedById(id: String): List<Seed> {
        var seeds = ArrayList<Seed>()

        seedList.forEach {
            if(it.olid == id) {
                seeds.add(it)
            }
        }
        return seeds
    }

    override fun getAllSeeds(): List<Seed> {
        return seedList
    }

    override fun saveSeed(seed: Seed): Boolean {
        seedList.add(seed)
        return true
    }

    override fun deleteSeed(seed: Seed): Boolean {
        seedList.remove(seed)
        return true
    }

    override fun saveBook(book: Book): Boolean {
        bookList.add(book)
        return true
    }

    override fun deleteBook(book: Book): Boolean {
        bookList.remove(book)
        return true
    }
}